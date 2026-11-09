package apijeurole.commun.domain;

public class RequeteInvalideException extends ErreurMetier {
    public RequeteInvalideException(String description) {
        super("INVALID_REQUEST", CategorieErreur.REQUETE_INVALIDE, description);
    }
}
