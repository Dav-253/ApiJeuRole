package apijeurole.lobby.domain.exception;

import apijeurole.commun.domain.CategorieErreur;

public class DejaDansUnLobbyException extends LobbyException {
    public DejaDansUnLobbyException() {
        super("ALREADY_IN_LOBBY", CategorieErreur.CONFLIT, "Ce joueur est déjà membre d'un lobby ouvert.");
    }
}