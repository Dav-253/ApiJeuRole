package main.api.dto;


import java.util.UUID;

public record JoinLobbyRequests(
        @NotNull UUID joueurId,
        @NotNull UUID heroId) {}
