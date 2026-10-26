package partie.domain.exception;

public class CombatDejaActifException extends PartieException {
    public CombatDejaActifException() {
        super("Un combat est déjà en cours : une partie ne peut pas avoir deux combats actifs.");
    }
}
