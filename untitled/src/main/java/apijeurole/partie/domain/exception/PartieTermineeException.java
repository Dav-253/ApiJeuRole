package apijeurole.partie.domain.exception;

public class PartieTermineeException extends PartieException {
    public PartieTermineeException() {
        super("La apijeurole.partie est terminée : plus aucune action n'est possible.");
    }
}
