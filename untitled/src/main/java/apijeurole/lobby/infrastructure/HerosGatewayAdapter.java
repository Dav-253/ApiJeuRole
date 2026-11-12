package apijeurole.lobby.infrastructure;

import apijeurole.heros.domain.HerosRepository;
import apijeurole.lobby.domain.HerosGateway;

import java.util.Optional;
import java.util.UUID;

/** Adaptateur : répond au port du lobby avec le dépôt du module heros. */
public class HerosGatewayAdapter implements HerosGateway {

    private final HerosRepository herosRepository;

    public HerosGatewayAdapter(HerosRepository herosRepository) {
        this.herosRepository = herosRepository;
    }

    @Override
    public Optional<HerosResume> trouver(UUID herosId) {
        return herosRepository.trouverParId(herosId)
                .map(h -> new HerosResume(h.getId(), h.getProprietaireId(), h.getNom()));
    }
}