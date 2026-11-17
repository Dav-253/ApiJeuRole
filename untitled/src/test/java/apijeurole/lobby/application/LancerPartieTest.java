package apijeurole.lobby.application;
import apijeurole.commun.domain.ErreurMetier;
import apijeurole.joueur.domain.Joueur;
import apijeurole.lobby.infrastructure.InMemoryLobbyRepository;
import apijeurole.partie.domain.MembreGroupe;
import apijeurole.partie.domain.Partie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
class LancerPartieTest extends LobbyServiceTestBase {
    private Joueur aria;
    private Joueur bram;
    private UUID thorin;
    private UUID eldrin;
    private UUID lobbyId;
    @BeforeEach
    void preparer() {
        service = new LobbyService(new InMemoryLobbyRepository(), joueurs, herosGateway, parties);
        aria = nouveauJoueur("Aria");
        bram = nouveauJoueur("Bram");
        thorin = nouveauHeros(aria, "Thorin");
        eldrin = nouveauHeros(bram, "Eldrin");
        lobbyId = service.creerLobby(aria.getId()).id();
        service.rejoindreLobby(lobbyId, bram.getId());
    }
    private void tousChoisissent() {
        service.choisirHeros(lobbyId, aria.getId(), thorin);
        service.choisirHeros(lobbyId, bram.getId(), eldrin);
    }
    @Test
    void l_hote_lance_la_partie_et_le_groupe_est_forme_des_heros_selectionnes() {
        tousChoisissent();
        PartieLancee partie = service.lancerPartie(lobbyId, aria.getId());
        assertEquals(lobbyId, partie.lobbyId());
        assertEquals(2, partie.heros().size());
        assertEquals(thorin, partie.heros().get(0).id());
        assertEquals("Thorin", partie.heros().get(0).nom());
        assertEquals(aria.getId(), partie.heros().get(0).joueurId());
        assertEquals(eldrin, partie.heros().get(1).id());
        Partie enregistree = parties.fetchById(partie.id());
        assertEquals(2, enregistree.getGroupe().size());
        assertEquals(lobbyId, enregistree.getLobbyId());
    }
    @Test
    void une_partie_peut_etre_lancee_avec_un_seul_joueur() {
        Joueur dax = nouveauJoueur("Dax");
        UUID lobbyDax = service.creerLobby(dax.getId()).id();
        service.choisirHeros(lobbyDax, dax.getId(), nouveauHeros(dax, "Gimli"));
        PartieLancee partie = service.lancerPartie(lobbyDax, dax.getId());
        assertEquals(1, partie.heros().size());
    }
    @Test
    void seul_l_hote_peut_lancer() {
        tousChoisissent();
        ErreurMetier erreur = assertThrows(ErreurMetier.class, () -> service.lancerPartie(lobbyId, bram.getId()));
        assertEquals("NOT_HOST", erreur.getCode());
    }
    @Test
    void tous_les_membres_doivent_avoir_selectionne_un_heros() {
        service.choisirHeros(lobbyId, aria.getId(), thorin);
        ErreurMetier erreur = assertThrows(ErreurMetier.class, () -> service.lancerPartie(lobbyId, aria.getId()));
        assertEquals("PLAYERS_NOT_READY", erreur.getCode());
        assertEquals("OPEN", service.consulterLobby(lobbyId).etat().name());
    }
    @Test
    void une_partie_deja_lancee_ne_peut_pas_etre_relancee() {
        tousChoisissent();
        service.lancerPartie(lobbyId, aria.getId());
        ErreurMetier erreur = assertThrows(ErreurMetier.class, () -> service.lancerPartie(lobbyId, aria.getId()));
        assertEquals("GAME_ALREADY_STARTED", erreur.getCode());
    }
    @Test
    void apres_le_lancement_plus_personne_ne_peut_rejoindre_quitter_ou_changer_de_heros() {
        tousChoisissent();
        service.lancerPartie(lobbyId, aria.getId());
        Joueur cora = nouveauJoueur("Cora");
        UUID autreHeros = nouveauHeros(aria, "Autre");
        assertEquals("GAME_ALREADY_STARTED", assertThrows(ErreurMetier.class,
                () -> service.rejoindreLobby(lobbyId, cora.getId())).getCode());
        assertEquals("GAME_ALREADY_STARTED", assertThrows(ErreurMetier.class,
                () -> service.quitterLobby(lobbyId, bram.getId())).getCode());
        assertEquals("GAME_ALREADY_STARTED", assertThrows(ErreurMetier.class,
                () -> service.choisirHeros(lobbyId, aria.getId(), autreHeros)).getCode());
    }
    @Test
    void un_lobby_lance_n_apparait_plus_dans_la_liste_et_ne_bloque_plus_ses_membres() {
        tousChoisissent();
        service.lancerPartie(lobbyId, aria.getId());
        assertEquals(0, service.listerLobbysRejoignables().size());
        service.creerLobby(aria.getId());
    }
    @Test
    void un_heros_deja_engage_dans_une_autre_partie_empeche_le_lancement() {
        tousChoisissent();
        // Thorin a été engagé entre-temps dans une autre partie
        parties.save(new Partie(UUID.randomUUID(), UUID.randomUUID(),
                List.of(new MembreGroupe(aria.getId(), thorin))));
        ErreurMetier erreur = assertThrows(ErreurMetier.class, () -> service.lancerPartie(lobbyId, aria.getId()));
        assertEquals("HERO_IN_GAME", erreur.getCode());
        assertEquals("OPEN", service.consulterLobby(lobbyId).etat().name());
    }
    @Test
    void un_lobby_inexistant_donne_LOBBY_NOT_FOUND() {
        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.lancerPartie(UUID.randomUUID(), aria.getId()));
        assertEquals("LOBBY_NOT_FOUND", erreur.getCode());
    }
}