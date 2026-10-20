package lobby.domain.exception;

public class JoueursPasPretsException extends LobbyException {
    public JoueursPasPretsException() {
        super("Impossible de lancer la partie : tous les joueurs ne sont pas prêts.");
    }
}
