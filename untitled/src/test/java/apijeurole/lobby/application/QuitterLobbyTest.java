package apijeurole.lobby.application;
import apijeurole.commun.domain.ErreurMetier;
import apijeurole.joueur.domain.Joueur;
import apijeurole.lobby.infrastructure.InMemoryLobbyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
class QuitterLobbyTest extends LobbyServiceTestBase {
    private Joueur aria;
    private Joueur bram;
    private Joueur cora;
    private UUID lobbyId;
    @BeforeEach
    void preparer() {
        service = new LobbyService(new InMemoryLobbyRepository(), joueurs, herosGateway, parties);
        aria = nouveauJoueur("Aria");
        bram = nouveauJoueur("Bram");
        cora = nouveauJoueur("Cora");
        lobbyId = service.creerLobby(aria.getId()).id();
        service.rejoindreLobby(lobbyId, bram.getId());
        service.rejoindreLobby(lobbyId, cora.getId());
    }
    @Test
    void un_membre_qui_quitte_libere_sa_place() {
        service.quitterLobby(lobbyId, bram.getId());
        LobbyVue lobby = service.consulterLobby(lobbyId);
        assertEquals(2, lobby.joueurs().size());
        assertEquals(2, lobby.placesDisponibles());
    }
    @Test
    void quand_l_hote_part_le_joueur_arrive_le_plus_tot_devient_hote() {
        service.quitterLobby(lobbyId, aria.getId());
        assertEquals(bram.getId(), service.consulterLobby(lobbyId).hostId());
    }
    @Test
    void quand_un_non_hote_part_l_hote_ne_change_pas() {
        service.quitterLobby(lobbyId, cora.getId());
        assertEquals(aria.getId(), service.consulterLobby(lobbyId).hostId());
    }
    @Test
    void quand_le_dernier_joueur_part_le_lobby_est_ferme_et_disparait() {
        service.quitterLobby(lobbyId, aria.getId());
        service.quitterLobby(lobbyId, bram.getId());
        service.quitterLobby(lobbyId, cora.getId());
        assertEquals(0, service.listerLobbysRejoignables().size());
        ErreurMetier erreur = assertThrows(ErreurMetier.class, () -> service.consulterLobby(lobbyId));
        assertEquals("LOBBY_NOT_FOUND", erreur.getCode());
    }
    @Test
    void un_joueur_qui_a_quitte_peut_rejoindre_un_autre_lobby() {
        UUID autreLobby = service.creerLobby(nouveauJoueur("Dax").getId()).id();
        service.quitterLobby(lobbyId, bram.getId());
        service.rejoindreLobby(autreLobby, bram.getId());
        assertEquals(2, service.consulterLobby(autreLobby).joueurs().size());
    }
    @Test
    void quitter_un_lobby_dont_on_n_est_pas_membre_donne_PLAYER_NOT_IN_LOBBY() {
        Joueur dax = nouveauJoueur("Dax");
        ErreurMetier erreur = assertThrows(ErreurMetier.class, () -> service.quitterLobby(lobbyId, dax.getId()));
        assertEquals("PLAYER_NOT_IN_LOBBY", erreur.getCode());
    }
    @Test
    void quitter_un_lobby_inexistant_donne_LOBBY_NOT_FOUND() {
        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.quitterLobby(UUID.randomUUID(), aria.getId()));
        assertEquals("LOBBY_NOT_FOUND", erreur.getCode());
    }
}