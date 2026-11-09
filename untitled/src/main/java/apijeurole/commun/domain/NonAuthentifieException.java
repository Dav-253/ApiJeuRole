package apijeurole.commun.domain;

public class NonAuthentifieException extends ErreurMetier {
    public NonAuthentifieException() {
        super("NOT_AUTHENTICATED", CategorieErreur.NON_AUTHENTIFIE, "Authentification requise.");
    }
}