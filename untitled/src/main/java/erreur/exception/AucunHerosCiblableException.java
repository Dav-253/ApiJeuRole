package erreur.exception;

public class AucunHerosCiblableException extends RuntimeException {

    public AucunHerosCiblableException() {
        super("Aucun héros actif à cibler.");
    }
}