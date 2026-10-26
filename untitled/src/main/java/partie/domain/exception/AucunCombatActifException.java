package partie.domain.exception;

public class AucunCombatActifException extends PartieException {
    public AucunCombatActifException() {
        super("Aucun combat n'est en cours dans cette partie.");
    }
}
