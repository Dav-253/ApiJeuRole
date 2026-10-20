package lobby.domain.exception;

public class LobbyFermeException extends LobbyException {
    public LobbyFermeException() {
        super("Le lobby n'est plus ouvert aux modifications (partie lancée ou terminée).");
    }
}
