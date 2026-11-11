package apijeurole.lobby.application;

import apijeurole.lobby.domain.EtatLobby;

import java.util.List;
import java.util.UUID;

/** Représentation d'un lobby pour l'extérieur : jamais de courriel, seulement des pseudonymes. */
public record LobbyVue(UUID id, EtatLobby etat, UUID hostId, List<MembreVue> joueurs, int placesDisponibles) {

    public record MembreVue(UUID joueurId, String pseudonyme, HerosVue heros) {}

    public record HerosVue(UUID id, String nom) {}
}