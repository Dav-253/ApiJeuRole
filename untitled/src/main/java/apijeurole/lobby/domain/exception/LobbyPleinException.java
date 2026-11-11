package apijeurole.lobby.domain.exception;

import apijeurole.commun.domain.CategorieErreur;

public class LobbyPleinException extends LobbyException {
    public LobbyPleinException() {
        super("LOBBY_FULL", CategorieErreur.CONFLIT, "Le lobby est plein (4 joueurs maximum).");
    }
}