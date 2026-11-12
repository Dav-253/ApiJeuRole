package apijeurole.lobby.domain.exception;

import apijeurole.commun.domain.CategorieErreur;

public class HerosEnPartieException extends LobbyException {
    public HerosEnPartieException() {
        super("HERO_IN_GAME", CategorieErreur.CONFLIT, "Ce héros est déjà engagé dans une partie en cours.");
    }
}