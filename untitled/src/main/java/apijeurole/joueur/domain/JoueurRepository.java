package apijeurole.joueur.domain;

import java.util.Optional;
import java.util.UUID;

public interface JoueurRepository {
    void save(Joueur joueur);
    Optional<Joueur> findById(UUID id);
    Optional<Joueur> findByCourriel(String courriel);
}
