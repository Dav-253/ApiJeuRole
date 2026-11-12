package apijeurole.lobby.domain.exception;

import apijeurole.commun.domain.CategorieErreur;

public class JoueurAbsentException extends LobbyException {
    public JoueurAbsentException() {
        super("PLAYER_NOT_IN_LOBBY", CategorieErreur.INTROUVABLE, "Ce joueur n'est pas membre de ce lobby.");
    }
}