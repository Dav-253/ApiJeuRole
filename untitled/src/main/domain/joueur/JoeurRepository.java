package main.domain.joueur;

import java.util.Optional;
import java.util.UUID;

public interface JoeurRepository {
    void save(Joeur joeur);
    Optional<Joeur> findById(UUID id);
    void deleteById(UUID id);
}
