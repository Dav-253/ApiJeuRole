package apijeurole.lobby.domain.exception;

import apijeurole.commun.domain.CategorieErreur;

public class PartieDejaLanceeException extends LobbyException {
    public PartieDejaLanceeException() {
        super("GAME_ALREADY_STARTED", CategorieErreur.CONFLIT, "La partie de ce lobby est déjà lancée.");
    }
}