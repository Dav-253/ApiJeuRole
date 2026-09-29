package erreur.exception;

public class InventairePleinException extends RuntimeException {
    public InventairePleinException(String message) {
        super(message);
    }
}
