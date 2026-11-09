package apijeurole.joueur.infrastructure.persistence;

import apijeurole.joueur.domain.Joueur;
import apijeurole.joueur.domain.JoueurRepository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryJoueurRepository implements JoueurRepository {

    private final Map<UUID, Joueur> dbSimulator  = new ConcurrentHashMap<>();

    @Override
    public void save(Joueur joueur) {
        if (joueur.getId() == null) {
            throw new IllegalArgumentException("L'identifiant du apijeurole.joueur ne peut pas être nul lors de la sauvegarde.");
        }

        dbSimulator.put(joueur.getId(), joueur);
    }

    @Override
    public Optional<Joueur> findById(UUID id) {
        return Optional.ofNullable(dbSimulator.get(id));
    }

    @Override
    public void deleteById(UUID id) {
        dbSimulator.remove(id);
    }

}
