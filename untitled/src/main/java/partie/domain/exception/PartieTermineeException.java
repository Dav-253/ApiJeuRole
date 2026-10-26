package partie.domain.exception;

public class PartieTermineeException extends PartieException {
    public PartieTermineeException() {
        super("La partie est terminée : plus aucune action n'est possible.");
    }
}
