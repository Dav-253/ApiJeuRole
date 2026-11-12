package apijeurole.lobby.application;

import apijeurole.commun.domain.ErreurMetier;
import apijeurole.joueur.domain.Joueur;
import apijeurole.lobby.domain.EtatLobby;
import apijeurole.lobby.infrastructure.InMemoryLobbyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreerLobbyTest extends LobbyServiceTestBase {

    @BeforeEach
    void preparer() {
        service = new LobbyService(new InMemoryLobbyRepository(), joueurs, herosGateway);
    }

    @Test
    void le_createur_devient_l_hote_et_le_premier_membre_avec_3_places_restantes() {
        Joueur aria = nouveauJoueur("Aria");

        LobbyVue lobby = service.creerLobby(aria.getId());

        assertEquals(EtatLobby.OPEN, lobby.etat());
        assertEquals(aria.getId(), lobby.hostId());
        assertEquals(1, lobby.joueurs().size());
        assertEquals("Aria", lobby.joueurs().get(0).pseudonyme());
        assertEquals(null, lobby.joueurs().get(0).heros());
        assertEquals(3, lobby.placesDisponibles());
    }

    @Test
    void un_joueur_deja_membre_d_un_lobby_ouvert_ne_peut_pas_en_creer_un_autre() {
        Joueur aria = nouveauJoueur("Aria");
        service.creerLobby(aria.getId());

        ErreurMetier erreur = assertThrows(ErreurMetier.class, () -> service.creerLobby(aria.getId()));

        assertEquals("ALREADY_IN_LOBBY", erreur.getCode());
    }

    @Test
    void deux_joueurs_differents_peuvent_chacun_creer_leur_lobby() {
        LobbyVue premier = service.creerLobby(nouveauJoueur("Aria").getId());
        LobbyVue second = service.creerLobby(nouveauJoueur("Bram").getId());

        assertEquals(false, premier.id().equals(second.id()));
    }
}