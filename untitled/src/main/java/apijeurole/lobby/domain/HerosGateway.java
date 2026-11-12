package apijeurole.lobby.domain;

import java.util.Optional;
import java.util.UUID;

/** Port : ce que le module lobby a besoin de savoir d'un héros, sans dépendre du module heros. */
public interface HerosGateway {
    Optional<HerosResume> trouver(UUID herosId);
    record HerosResume(UUID id, UUID proprietaireId, String nom) {}
}