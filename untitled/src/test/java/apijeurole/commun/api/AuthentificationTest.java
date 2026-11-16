package apijeurole.commun.api;
import apijeurole.commun.domain.ErreurMetier;
import jakarta.ws.rs.core.SecurityContext;
import org.junit.jupiter.api.Test;
import java.security.Principal;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
class AuthentificationTest {
    private static SecurityContext contexte(UUID joueurId) {
        return new SecurityContext() {
            public Principal getUserPrincipal() {
                return joueurId == null ? null : joueurId::toString;
            }
            public boolean isUserInRole(String role) {
                return false;
            }
            public boolean isSecure() {
                return false;
            }
            public String getAuthenticationScheme() {
                return "Bearer";
            }
        };
    }
    @Test
    void le_joueur_authentifie_est_lu_dans_le_contexte() {
        UUID id = UUID.randomUUID();
        assertEquals(id, Authentification.joueurId(contexte(id)));
    }
    @Test
    void sans_joueur_dans_le_contexte_c_est_NOT_AUTHENTICATED() {
        ErreurMetier erreur = assertThrows(ErreurMetier.class, () -> Authentification.joueurId(contexte(null)));
        assertEquals("NOT_AUTHENTICATED", erreur.getCode());
    }
    @Test
    void agir_pour_un_autre_joueur_est_FORBIDDEN() {
        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> Authentification.exigerMemeJoueur(contexte(UUID.randomUUID()), UUID.randomUUID()));
        assertEquals("FORBIDDEN", erreur.getCode());
    }
    @Test
    void agir_pour_soi_meme_est_permis() {
        UUID id = UUID.randomUUID();
        assertEquals(id, Authentification.exigerMemeJoueur(contexte(id), id));
    }
}
