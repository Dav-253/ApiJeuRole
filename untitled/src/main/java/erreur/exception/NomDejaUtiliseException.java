package erreur.exception;

public class NomDejaUtiliseException extends RuntimeException {
    public NomDejaUtiliseException(String message) {
        super(message);
    }
}