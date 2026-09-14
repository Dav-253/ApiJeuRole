package main.erreur.exception;

public class NomPersonnageInvalideException extends RuntimeException {
    public NomPersonnageInvalideException(String message) {
        super(message);
    }
}
