package apijeurole.partie.domain;

import java.util.Objects;
import java.util.UUID;

public record MembreGroupe(UUID joueurId, UUID herosId) {
    public MembreGroupe {
        Objects.requireNonNull(joueurId, "Le apijeurole.joueur est obligatoire");
        Objects.requireNonNull(herosId, "Le héros est obligatoire");
    }
}
