package apijeurole.joueur.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthentificationFilterTest {

    @Test
    void seules_les_routes_post_players_et_post_sessions_sont_publiques() {
        assertEquals(true, AuthentificationFilter.estRoutePublique("POST", "players"));
        assertEquals(true, AuthentificationFilter.estRoutePublique("POST", "/sessions/"));
        assertEquals(false, AuthentificationFilter.estRoutePublique("GET", "players"));
        assertEquals(false, AuthentificationFilter.estRoutePublique("GET", "players/abc"));
        assertEquals(false, AuthentificationFilter.estRoutePublique("POST", "lobbies"));
        assertEquals(false, AuthentificationFilter.estRoutePublique("POST", "players/abc/heros"));
    }

    @Test
    void le_jeton_est_lu_dans_l_en_tete_bearer() {
        assertEquals("abc", AuthentificationFilter.extraireJeton("Bearer abc").get());
        assertEquals(false, AuthentificationFilter.extraireJeton(null).isPresent());
        assertEquals(false, AuthentificationFilter.extraireJeton("Basic abc").isPresent());
        assertEquals(false, AuthentificationFilter.extraireJeton("Bearer ").isPresent());
    }
}