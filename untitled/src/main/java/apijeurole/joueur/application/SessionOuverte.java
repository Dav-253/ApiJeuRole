package apijeurole.joueur.application;

import java.util.UUID;

public record SessionOuverte(UUID joueurId, String jeton) {}
