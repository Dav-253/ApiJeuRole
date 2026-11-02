package domain.heros;

import domain.heros.autre.Nom;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Port du domaine pour la persistance des héros.
 * Étend NomRepository pour pouvoir être passé directement à HerosFactory.
 */
public interface HerosRepository extends HerosFactory.NomRepository {

    /**
     * Enregistre le héros. Le nom doit être unique dans tout le système (insensible à la casse).
     * @throws erreur.exception.NomDejaUtiliseException si un autre héros porte déjà ce nom
     */
    void sauvegarder(Heros heros);

    Optional<Heros> trouverParId(UUID id);

    List<Heros> trouverParProprietaire(UUID proprietaireId);

    @Override
    boolean nomExiste(Nom nom);
}
