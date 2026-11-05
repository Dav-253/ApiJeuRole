package apijeurole.lobby.domain.exception;

public class LobbyFermeException extends LobbyException {
    public LobbyFermeException() {
        super("Le apijeurole.lobby n'est plus ouvert aux modifications (apijeurole.partie lancée ou terminée).");
    }
}
