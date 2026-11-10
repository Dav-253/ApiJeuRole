package apijeurole.joueur.infrastructure;

import apijeurole.joueur.application.GestionJetons;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryGestionJetons implements GestionJetons {

    private final SecureRandom aleatoire = new SecureRandom();
    private final Map<String, UUID> joueurParJeton = new ConcurrentHashMap<>();

    @Override
    public String creerJeton(UUID joueurId) {
        byte[] octets = new byte[32];
        aleatoire.nextBytes(octets);
        String jeton = Base64.getUrlEncoder().withoutPadding().encodeToString(octets);
        joueurParJeton.put(jeton, joueurId);
        return jeton;
    }

    @Override
    public Optional<UUID> trouverJoueur(String jeton) {
        return jeton == null ? Optional.empty() : Optional.ofNullable(joueurParJeton.get(jeton));
    }
}