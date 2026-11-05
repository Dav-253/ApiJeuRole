package apijeurole.lobby.domain.exception;

/** Racine des erreurs de règle du module apijeurole.lobby (HTTP 422, sauf LobbyIntrouvable → 404). */
public abstract class LobbyException extends RuntimeException {
    protected LobbyException(String message) {
        super(message);
    }
}
