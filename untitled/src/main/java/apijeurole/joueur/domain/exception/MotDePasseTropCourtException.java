package apijeurole.joueur.domain.exception;

import apijeurole.commun.domain.CategorieErreur;
import apijeurole.commun.domain.ErreurMetier;

public class MotDePasseTropCourtException extends ErreurMetier {
    public MotDePasseTropCourtException(int longueurMin) {
        super("PASSWORD_TOO_SHORT", CategorieErreur.REGLE_REFUSEE,
                "Le mot de passe doit contenir au moins " + longueurMin + " caractères.");
    }
}