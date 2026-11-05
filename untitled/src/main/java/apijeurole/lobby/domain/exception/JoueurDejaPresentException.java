package apijeurole.lobby.domain.exception;

public class JoueurDejaPresentException extends LobbyException {
    public JoueurDejaPresentException() {
        super("Ce apijeurole.joueur est déjà présent dans le apijeurole.lobby.");
    }
}
