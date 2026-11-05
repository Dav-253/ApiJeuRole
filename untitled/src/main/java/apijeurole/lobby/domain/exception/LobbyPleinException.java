package apijeurole.lobby.domain.exception;

public class LobbyPleinException extends LobbyException {
    public LobbyPleinException() {
        super("Le apijeurole.lobby est plein (4 joueurs maximum).");
    }
}
