package lobby.domain.exception;

public class LobbyVideException extends LobbyException {
    public LobbyVideException() {
        super("Impossible de lancer la partie : le lobby ne contient aucun joueur.");
    }
}
