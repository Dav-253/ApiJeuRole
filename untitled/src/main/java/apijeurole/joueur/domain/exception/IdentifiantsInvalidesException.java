package apijeurole.joueur.domain.exception;

import apijeurole.commun.domain.CategorieErreur;
import apijeurole.commun.domain.ErreurMetier;

public class IdentifiantsInvalidesException extends ErreurMetier {
    public IdentifiantsInvalidesException() {
        super("INVALID_CREDENTIALS", CategorieErreur.NON_AUTHENTIFIE, "Courriel ou mot de passe invalide.");
    }
}