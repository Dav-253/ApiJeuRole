package apijeurole.partie.domain.exception;

public class GroupeInvalideException extends PartieException {
    public GroupeInvalideException(String raison) {
        super("Le groupe doit compter de 1 à 4 héros, chacun avec un apijeurole.joueur et un héros différents. " + raison);
    }
}
