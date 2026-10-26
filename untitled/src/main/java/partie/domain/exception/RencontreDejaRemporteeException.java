package partie.domain.exception;

public class RencontreDejaRemporteeException extends PartieException {
    public RencontreDejaRemporteeException(String rencontre) {
        super("La rencontre « " + rencontre + " » a déjà été remportée : elle ne peut pas être recommencée.");
    }
}
