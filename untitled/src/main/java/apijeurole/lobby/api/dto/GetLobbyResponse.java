package apijeurole.lobby.api.dto;

import java.util.List;
import java.util.UUID;

public record GetLobbyResponse(
        UUID lobbyId,
        String etatLobby,
        int nombreJoueursActuels,
        List<UUID> joueursConnectes) {}
