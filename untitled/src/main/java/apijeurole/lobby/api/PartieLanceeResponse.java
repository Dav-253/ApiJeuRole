package apijeurole.lobby.api;
import apijeurole.lobby.application.PartieLancee;
import java.util.List;
public record PartieLanceeResponse(String id, String lobbyId, List<HeroResponse> heroes) {
    public record HeroResponse(String id, String name, String playerId) {}
    public static PartieLanceeResponse de(PartieLancee partie) {
        return new PartieLanceeResponse(
                partie.id().toString(),
                partie.lobbyId().toString(),
                partie.heros().stream()
                        .map(h -> new HeroResponse(h.id().toString(), h.nom(), h.joueurId().toString()))
                        .toList());
    }
}