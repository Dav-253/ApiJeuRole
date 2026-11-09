package apijeurole.joueur.domain.exception;

import apijeurole.commun.domain.CategorieErreur;
import apijeurole.commun.domain.ErreurMetier;

public class CourrielDejaUtiliseException extends ErreurMetier {
    public CourrielDejaUtiliseException() {
        super("EMAIL_TAKEN", CategorieErreur.CONFLIT, "Ce courriel est déjà utilisé.");
    }
}