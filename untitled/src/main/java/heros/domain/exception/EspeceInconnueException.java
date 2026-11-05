package heros.domain.exception;

public class EspeceInconnueException extends IllegalArgumentException {
    public EspeceInconnueException(String message) {
        super(message);
    }
}