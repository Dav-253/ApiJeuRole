package apijeurole.lobby.domain.exception;

import apijeurole.commun.domain.CategorieErreur;

import java.util.UUID;

public class HerosIntrouvableException extends LobbyException {
    public HerosIntrouvableException(UUID id) {
        super("HERO_NOT_FOUND", CategorieErreur.INTROUVABLE, "Héros introuvable : " + id);
    }
}