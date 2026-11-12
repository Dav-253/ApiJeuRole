package apijeurole.lobby.application;

import apijeurole.commun.domain.ErreurMetier;
import apijeurole.joueur.domain.Joueur;
import apijeurole.lobby.infrastructure.InMemoryLobbyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConsulterLobbysTest extends LobbyServiceTestBase {

    @BeforeEach
    void preparer() {
        service = new LobbyService(new InMemoryLobbyRepository(), joueurs, herosGateway);
    }

    @Test
    void la_liste_montre_l_hote_et_le_nombre_de_joueurs_sur_4() {
        Joueur aria = nouveauJoueur("Aria");
        LobbyVue lobby = service.creerLobby(aria.getId());

        List<LobbyResume> liste = service.listerLobbysRejoignables();

        assertEquals(1, liste.size());
        assertEquals(lobby.id(), liste.get(0).id());
        assertEquals(aria.getId(), liste.get(0).hostId());
        assertEquals(1, liste.get(0).nombreJoueurs());
        assertEquals(4, liste.get(0).maximumJoueurs());
    }

    @Test
    void la_liste_est_vide_sans_lobby() {
        assertEquals(0, service.listerLobbysRejoignables().size());
    }

    @Test
    void le_detail_montre_l_hote_l_etat_et_les_pseudonymes() {
        Joueur aria = nouveauJoueur("Aria");
        LobbyVue cree = service.creerLobby(aria.getId());

        LobbyVue detail = service.consulterLobby(cree.id());

        assertEquals(cree.id(), detail.id());
        assertEquals(aria.getId(), detail.hostId());
        assertEquals("OPEN", detail.etat().name());
        assertEquals("Aria", detail.joueurs().get(0).pseudonyme());
    }

    @Test
    void un_lobby_inexistant_donne_LOBBY_NOT_FOUND() {
        ErreurMetier erreur = assertThrows(ErreurMetier.class, () -> service.consulterLobby(UUID.randomUUID()));

        assertEquals("LOBBY_NOT_FOUND", erreur.getCode());
    }
}