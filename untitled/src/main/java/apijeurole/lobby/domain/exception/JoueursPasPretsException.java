package apijeurole.lobby.domain.exception;

import apijeurole.commun.domain.CategorieErreur;

public class JoueursPasPretsException extends LobbyException {
    public JoueursPasPretsException() {
        super("PLAYERS_NOT_READY", CategorieErreur.CONFLIT, "Chaque membre doit avoir sélectionné un héros.");
    }
}