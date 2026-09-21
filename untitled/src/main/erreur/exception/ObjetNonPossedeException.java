package main.erreur.exception;

public class ObjetNonPossedeException extends RuntimeException {
    public ObjetNonPossedeException(String message) {
        super(message);
    }
}
