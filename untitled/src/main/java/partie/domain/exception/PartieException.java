package partie.domain.exception;

/** Racine des erreurs de règle du module partie (HTTP 422, sauf PartieIntrouvable → 404). */
public abstract class PartieException extends RuntimeException {
    protected PartieException(String message) {
        super(message);
    }
}
