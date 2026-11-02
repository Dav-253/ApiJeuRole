package infrastructure.persistence;

import domain.heros.Heros;
import domain.heros.HerosRepository;
import domain.heros.autre.Nom;
import erreur.exception.NomDejaUtiliseException;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryHeroRepository implements HerosRepository {

    private final ConcurrentHashMap<UUID, Heros> herosParId = new ConcurrentHashMap<>();
    // Index des noms (en minuscules) -> id du héros ; sert à garantir l'unicité
    private final ConcurrentHashMap<String, UUID> idParNom = new ConcurrentHashMap<>();

    @Override
    public void sauvegarder(Heros heros) {
        Objects.requireNonNull(heros, "Le héros est obligatoire.");

        // putIfAbsent est atomique : deux requêtes simultanées avec le même nom ne passent pas toutes les deux
        UUID existant = idParNom.putIfAbsent(cle(heros.getNom()), heros.getId());
        if (existant != null && !existant.equals(heros.getId())) {
            throw new NomDejaUtiliseException("Le nom « " + heros.getNom() + " » est déjà utilisé.");
        }
        herosParId.put(heros.getId(), heros);
    }

    @Override
    public Optional<Heros> trouverParId(UUID id) {
        return Optional.ofNullable(herosParId.get(id));
    }

    @Override
    public List<Heros> trouverParProprietaire(UUID proprietaireId) {
        return herosParId.values().stream()
                .filter(h -> h.getProprietaireId().equals(proprietaireId))
                .toList();
    }

    @Override
    public boolean nomExiste(Nom nom) {
        return idParNom.containsKey(cle(nom.getValue()));
    }

    private static String cle(String nom) {
        return nom.toLowerCase(Locale.ROOT);
    }
}