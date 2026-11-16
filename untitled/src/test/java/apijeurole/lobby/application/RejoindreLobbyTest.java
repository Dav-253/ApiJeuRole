package apijeurole.lobby.application;
import apijeurole.commun.domain.ErreurMetier;
import apijeurole.joueur.domain.Joueur;
import apijeurole.lobby.infrastructure.InMemoryLobbyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
class RejoindreLobbyTest extends LobbyServiceTestBase {
    private Joueur aria;
    private LobbyVue lobby;
    @BeforeEach
    void preparer() {
        service = new LobbyService(new InMemoryLobbyRepository(), joueurs, herosGateway, parties);
        aria = nouveauJoueur("Aria");
        lobby = service.creerLobby(aria.getId());
    }
    @Test
    void un_joueur_rejoint_un_lobby_ouvert_tant_qu_il_reste_une_place() {
        Joueur bram = nouveauJoueur("Bram");
        LobbyVue mis_a_jour = service.rejoindreLobby(lobby.id(), bram.getId());
        assertEquals(2, mis_a_jour.joueurs().size());
        assertEquals("Bram", mis_a_jour.joueurs().get(1).pseudonyme());
        assertEquals(2, mis_a_jour.placesDisponibles());
        assertEquals(aria.getId(), mis_a_jour.hostId());
    }
    @Test
    void le_cinquieme_joueur_est_refuse() {
        service.rejoindreLobby(lobby.id(), nouveauJoueur("Bram").getId());
        service.rejoindreLobby(lobby.id(), nouveauJoueur("Cora").getId());
        service.rejoindreLobby(lobby.id(), nouveauJoueur("Dax").getId());
        Joueur eli = nouveauJoueur("Eli");
        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.rejoindreLobby(lobby.id(), eli.getId()));
        assertEquals("LOBBY_FULL", erreur.getCode());
        assertEquals(4, service.consulterLobby(lobby.id()).joueurs().size());
    }
    @Test
    void un_lobby_plein_n_apparait_plus_dans_la_liste() {
        service.rejoindreLobby(lobby.id(), nouveauJoueur("Bram").getId());
        service.rejoindreLobby(lobby.id(), nouveauJoueur("Cora").getId());
        assertEquals(1, service.listerLobbysRejoignables().size());
        service.rejoindreLobby(lobby.id(), nouveauJoueur("Dax").getId());
        assertEquals(0, service.listerLobbysRejoignables().size());
    }
    @Test
    void un_joueur_deja_membre_du_lobby_ne_peut_pas_le_rejoindre_de_nouveau() {
        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.rejoindreLobby(lobby.id(), aria.getId()));
        assertEquals("ALREADY_MEMBER", erreur.getCode());
    }
    @Test
    void un_joueur_membre_d_un_autre_lobby_ouvert_ne_peut_pas_en_rejoindre_un_deuxieme() {
        Joueur bram = nouveauJoueur("Bram");
        service.creerLobby(bram.getId());
        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.rejoindreLobby(lobby.id(), bram.getId()));
        assertEquals("ALREADY_IN_LOBBY", erreur.getCode());
    }
    @Test
    void rejoindre_un_lobby_inexistant_donne_LOBBY_NOT_FOUND() {
        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.rejoindreLobby(UUID.randomUUID(), nouveauJoueur("Bram").getId()));
        assertEquals("LOBBY_NOT_FOUND", erreur.getCode());
    }
}