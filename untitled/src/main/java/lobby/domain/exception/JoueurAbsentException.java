package lobby.domain.exception;

public class JoueurAbsentException extends LobbyException {
    public JoueurAbsentException() {
        super("Ce joueur n'est pas présent dans le lobby.");
    }
}
