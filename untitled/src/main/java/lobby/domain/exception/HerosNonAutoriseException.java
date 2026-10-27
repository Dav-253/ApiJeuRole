package lobby.domain.exception;

public class HerosNonAutoriseException extends LobbyException {
    public HerosNonAutoriseException() {
        super("Ce héros n'appartient pas à ce joueur.");
    }
}
