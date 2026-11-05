package apijeurole.lobby.domain.exception;

public class JoueurAbsentException extends LobbyException {
    public JoueurAbsentException() {
        super("Ce apijeurole.joueur n'est pas présent dans le apijeurole.lobby.");
    }
}
