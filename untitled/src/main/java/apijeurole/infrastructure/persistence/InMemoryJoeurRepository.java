package apijeurole.infrastructure.persistence;

import apijeurole.domain.joueur.Joeur;
import apijeurole.domain.joueur.JoeurRepository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryJoeurRepository implements JoeurRepository {

    private final Map<UUID, Joeur> dbSimulator  = new ConcurrentHashMap<>();

    @Override
    public void save(Joeur joeur) {
        if (joeur.getId() == null) {
            throw new IllegalArgumentException("L'identifiant du apijeurole.joueur ne peut pas être nul lors de la sauvegarde.");
        }

        dbSimulator.put(joeur.getId(), joeur);
    }

    @Override
    public Optional<Joeur> findById(UUID id) {
        return Optional.ofNullable(dbSimulator.get(id));
    }

    @Override
    public void deleteById(UUID id) {
        dbSimulator.remove(id);
    }

}
