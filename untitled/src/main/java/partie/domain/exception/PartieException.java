package partie.domain.exception;

import java.util.Set;
import java.util.UUID;

public class HerosDejaEnPartieException extends PartieException {
    public HerosDejaEnPartieException(Set<UUID> herosIds) {
        super("Ces héros sont déjà dans une partie en cours : " + herosIds);
    }
}