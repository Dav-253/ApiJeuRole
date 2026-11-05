package apijeurole.lobby.domain.exception;

public class LobbyVideException extends LobbyException {
    public LobbyVideException() {
        super("Impossible de lancer la apijeurole.partie : le apijeurole.lobby ne contient aucun apijeurole.joueur.");
    }
}
