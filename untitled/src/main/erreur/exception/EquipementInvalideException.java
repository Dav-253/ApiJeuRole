package main.erreur.exception;

public class EquipementInvalideException extends RuntimeException {
    public EquipementInvalideException(String message) {
        super(message);
    }
}
