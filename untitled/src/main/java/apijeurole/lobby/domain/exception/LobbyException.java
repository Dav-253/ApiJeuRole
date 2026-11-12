package apijeurole.lobby.domain.exception;

import apijeurole.commun.domain.CategorieErreur;
import apijeurole.commun.domain.ErreurMetier;

/** Racine des erreurs du module lobby. */
public abstract class LobbyException extends ErreurMetier {
    protected LobbyException(String code, CategorieErreur categorie, String description) {
        super(code, categorie, description);
    }
}