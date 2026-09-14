package main.erreur.exception;

public class PmInvalidException extends RuntimeException {
    public PmInvalidException(String message) {
        super(message);
    }
}
