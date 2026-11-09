package apijeurole.joueur.domain.exception;

import apijeurole.commun.domain.CategorieErreur;
import apijeurole.commun.domain.ErreurMetier;

public class PseudonymeDejaUtiliseException extends ErreurMetier {
    public PseudonymeDejaUtiliseException(String pseudonyme) {
        super("PSEUDONYM_TAKEN", CategorieErreur.CONFLIT, "Le pseudonyme « " + pseudonyme + " » est déjà utilisé.");
    }
}