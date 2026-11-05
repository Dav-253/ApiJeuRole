package apijeurole.lobby.domain.exception;

import java.util.UUID;

public class LobbyIntrouvableException extends LobbyException {
    public LobbyIntrouvableException(UUID lobbyId) {
        super("Lobby introuvable : " + lobbyId);
    }
}
