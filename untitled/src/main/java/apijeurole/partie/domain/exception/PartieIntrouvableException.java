package apijeurole.partie.domain.exception;

import java.util.UUID;

public class PartieIntrouvableException extends PartieException {
    public PartieIntrouvableException(UUID partieId) {
        super("Partie introuvable : " + partieId);
    }
}
