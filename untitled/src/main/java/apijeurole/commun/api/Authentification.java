package apijeurole.commun.api;

import apijeurole.commun.domain.ActionInterditeException;
import apijeurole.commun.domain.NonAuthentifieException;
import jakarta.ws.rs.core.SecurityContext;

import java.util.UUID;

public final class Authentification {

    private Authentification() {}

    public static UUID joueurId(SecurityContext contexte) {
        if (contexte == null || contexte.getUserPrincipal() == null) {
            throw new NonAuthentifieException();
        }

        try {
            return UUID.fromString(contexte.getUserPrincipal().getName());
        } catch (IllegalArgumentException e) {
            throw new NonAuthentifieException();
        }
    }

    public static UUID exigerMemeJoueur(SecurityContext contexte, UUID joueurId) {
        UUID authentifie = joueurId(contexte);
        if (!authentifie.equals(joueurId)) {
            throw new ActionInterditeException("Cette action est réservée au joueur concerné.");
        }
        return authentifie;
    }

}
