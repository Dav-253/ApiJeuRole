package apijeurole.lobby.application;

import java.util.UUID;

/** Ligne de la liste des lobbys qu'on peut rejoindre. */
public record LobbyResume(UUID id, UUID hostId, int nombreJoueurs, int maximumJoueurs) {}

