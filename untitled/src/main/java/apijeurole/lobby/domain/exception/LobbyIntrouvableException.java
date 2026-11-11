package apijeurole.lobby.domain.exception;

import apijeurole.commun.domain.CategorieErreur;

import java.util.UUID;

public class LobbyIntrouvableException extends LobbyException {
    public LobbyIntrouvableException(UUID id) {
        super("LOBBY_NOT_FOUND", CategorieErreur.INTROUVABLE, "Lobby introuvable : " + id);
    }
}