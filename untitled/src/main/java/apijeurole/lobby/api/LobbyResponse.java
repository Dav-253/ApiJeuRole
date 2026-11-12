package apijeurole.lobby.api;

import apijeurole.lobby.application.LobbyVue;

import java.util.List;

/** Représentation d'un lobby (L1, L2, L3, L5). Le courriel des joueurs n'y figure jamais. */
public record LobbyResponse(String id, String state, String hostId, List<MembreResponse> players,
                            int availableSeats) {

    public record MembreResponse(String playerId, String pseudonym, HeroResponse hero) {}

    public record HeroResponse(String id, String name) {}

    public static LobbyResponse de(LobbyVue vue) {
        return new LobbyResponse(
                vue.id().toString(),
                vue.etat().name(),
                vue.hostId().toString(),
                vue.joueurs().stream()
                        .map(m -> new MembreResponse(
                                m.joueurId().toString(),
                                m.pseudonyme(),
                                m.heros() == null ? null
                                        : new HeroResponse(m.heros().id().toString(), m.heros().nom())))
                        .toList(),
                vue.placesDisponibles());
    }

}