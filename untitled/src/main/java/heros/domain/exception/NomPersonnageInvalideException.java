package heros.domain.exception;

public class NomPersonnageInvalideException extends IllegalArgumentException {
    public NomPersonnageInvalideException(String message) {
        super(message);
    }
}
