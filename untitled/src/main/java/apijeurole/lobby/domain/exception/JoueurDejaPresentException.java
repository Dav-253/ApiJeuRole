package apijeurole.lobby.domain.exception;

import apijeurole.commun.domain.CategorieErreur;

public class JoueurDejaPresentException extends LobbyException {
    public JoueurDejaPresentException() {
        super("ALREADY_MEMBER", CategorieErreur.CONFLIT, "Ce joueur est déjà membre de ce lobby.");
    }
}