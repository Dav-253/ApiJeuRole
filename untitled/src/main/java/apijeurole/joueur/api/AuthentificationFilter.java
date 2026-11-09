package apijeurole.joueur.api;


import apijeurole.commun.domain.NonAuthentifieException;
import apijeurole.joueur.application.GestionJetons;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthentificationFilter implements ContainerRequestFilter {

    private static final String PREFIXE = "Bearer ";

    private final GestionJetons gestionJetons;

    public AuthentificationFilter(GestionJetons gestionJetons) {
        this.gestionJetons = gestionJetons;
    }

    @Override
    public void filter(ContainerRequestContext requete) {
        if (estRoutePublique(requete.getMethod(), requete.getUriInfo().getPath())) {
            return;
        }
        UUID joueurId = extraireJeton(requete.getHeaderString(HttpHeaders.AUTHORIZATION))
                .flatMap(gestionJetons::trouverJoueur)
                .orElseThrow(NonAuthentifieException::new);

        requete.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return joueurId::toString;
            }

            @Override
            public boolean isUserInRole(String role) {
                return false;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public String getAuthenticationScheme() {
                return "Bearer";
            }
        });
    }

    static boolean estRoutePublique(String methode, String chemin) {
        String propre = chemin == null ? "" : chemin.replaceAll("^/+|/+$", "");
        return "POST".equalsIgnoreCase(methode) && (propre.equals("players") || propre.equals("sessions"));
    }

    static Optional<String> extraireJeton(String enteteAuthorization) {
        if (enteteAuthorization == null || !enteteAuthorization.startsWith(PREFIXE)) {
            return Optional.empty();
        }
        String jeton = enteteAuthorization.substring(PREFIXE.length()).trim();
        return jeton.isEmpty() ? Optional.empty() : Optional.of(jeton);
    }
}