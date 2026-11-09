package apijeurole.commun.domain;

public class ActionInterditeException extends ErreurMetier {
    public ActionInterditeException(String description) {
        super("FORBIDDEN", CategorieErreur.INTERDIT, description);
    }
}
