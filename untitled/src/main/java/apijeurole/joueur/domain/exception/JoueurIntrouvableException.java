package apijeurole.joueur.domain.exception;

import apijeurole.commun.domain.CategorieErreur;
import apijeurole.commun.domain.ErreurMetier;

import java.util.UUID;

public class JoueurIntrouvableException extends ErreurMetier {
    public JoueurIntrouvableException(UUID id) {
        super("PLAYER_NOT_FOUND", CategorieErreur.INTROUVABLE, "Joueur introuvable : " + id);
    }
}