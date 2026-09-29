package api.dto;

import java.util.List;
import java.util.UUID;

public record GetLobbyResponse(
        UUID lobbyId,
        String etatLobby,
        int nombreJoeursActuels,
        List<UUID> joeursConnectes) {}
