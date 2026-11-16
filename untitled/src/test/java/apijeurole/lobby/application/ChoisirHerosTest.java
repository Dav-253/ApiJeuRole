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
class ChoisirHerosTest extends LobbyServiceTestBase {
    private Joueur aria;
    private Joueur bram;
    private UUID lobbyId;
    @BeforeEach
    void preparer() {
        service = new LobbyService(new InMemoryLobbyRepository(), joueurs, herosGateway, parties);
        aria = nouveauJoueur("Aria");
        bram = nouveauJoueur("Bram");
        lobbyId = service.creerLobby(aria.getId()).id();
        service.rejoindreLobby(lobbyId, bram.getId());
    }
    @Test
    void le_choix_est_visible_par_tous_les_membres_du_lobby() {
        UUID thorin = nouveauHeros(aria, "Thorin");
        service.choisirHeros(lobbyId, aria.getId(), thorin);
        LobbyVue lobby = service.consulterLobby(lobbyId);
        assertEquals("Thorin", lobby.joueurs().get(0).heros().nom());
        assertEquals(thorin, lobby.joueurs().get(0).heros().id());
        assertEquals(null, lobby.joueurs().get(1).heros());
    }
    @Test
    void une_nouvelle_selection_remplace_la_precedente() {
        UUID thorin = nouveauHeros(aria, "Thorin");
        UUID eldrin = nouveauHeros(aria, "Eldrin");
        service.choisirHeros(lobbyId, aria.getId(), thorin);
        LobbyVue lobby = service.choisirHeros(lobbyId, aria.getId(), eldrin);
        assertEquals("Eldrin", lobby.joueurs().get(0).heros().nom());
    }
    @Test
    void on_ne_peut_pas_choisir_le_heros_d_un_autre_joueur() {
        UUID heroDeBram = nouveauHeros(bram, "Gimli");
        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.choisirHeros(lobbyId, aria.getId(), heroDeBram));
        assertEquals("FORBIDDEN", erreur.getCode());
    }
    @Test
    void un_heros_inexistant_donne_HERO_NOT_FOUND() {
        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.choisirHeros(lobbyId, aria.getId(), UUID.randomUUID()));
        assertEquals("HERO_NOT_FOUND", erreur.getCode());
    }
    @Test
    void un_joueur_qui_n_est_pas_membre_donne_PLAYER_NOT_IN_LOBBY() {
        Joueur cora = nouveauJoueur("Cora");
        UUID heros = nouveauHeros(cora, "Legolas");
        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.choisirHeros(lobbyId, cora.getId(), heros));
        assertEquals("PLAYER_NOT_IN_LOBBY", erreur.getCode());
    }
    @Test
    void un_lobby_inexistant_donne_LOBBY_NOT_FOUND() {
        UUID thorin = nouveauHeros(aria, "Thorin");
        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.choisirHeros(UUID.randomUUID(), aria.getId(), thorin));
        assertEquals("LOBBY_NOT_FOUND", erreur.getCode());
    }
    @Test
    void un_heros_deja_dans_une_partie_en_cours_est_refuse() {
        UUID thorin = nouveauHeros(aria, "Thorin");
        parties.save(new Partie(UUID.randomUUID(), UUID.randomUUID(),
                List.of(new MembreGroupe(aria.getId(), thorin))));
        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.choisirHeros(lobbyId, aria.getId(), thorin));
        assertEquals("HERO_IN_GAME", erreur.getCode());
    }
    @Test
    void en_quittant_le_lobby_le_heros_n_est_plus_selectionne() {
        service.choisirHeros(lobbyId, bram.getId(), nouveauHeros(bram, "Gimli"));
        service.quitterLobby(lobbyId, bram.getId());
        LobbyVue lobby = service.rejoindreLobby(lobbyId, bram.getId());
        assertEquals(null, lobby.joueurs().get(1).heros());
    }
}