package lobby.domain.exception;

public class LobbyPleinException extends LobbyException {
    public LobbyPleinException() {
        super("Le lobby est plein (4 joueurs maximum).");
    }
}
