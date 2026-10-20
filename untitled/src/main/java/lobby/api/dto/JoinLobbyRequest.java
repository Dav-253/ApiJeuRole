package lobby.api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;


public record JoinLobbyRequest(
        @NotNull UUID joueurId,
        @NotNull UUID herosId) {}
