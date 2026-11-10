package apijeurole.joueur.application;

import java.util.Optional;
import java.util.UUID;

public interface GestionJetons {
    String creerJeton(UUID joueurId);
    Optional<UUID> trouverJoueur(String jeton);
}
