package lobby.domain.exception;

public class JoueurDejaPresentException extends LobbyException {
    public JoueurDejaPresentException() {
        super("Ce joueur est déjà présent dans le lobby.");
    }
}
