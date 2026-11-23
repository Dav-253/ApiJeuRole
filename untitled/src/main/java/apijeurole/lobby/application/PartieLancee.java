package apijeurole.lobby.application;
import java.util.List;
import java.util.UUID;
/** Résultat du lancement : la partie créée et son groupe de héros. */
public record PartieLancee(UUID id, UUID lobbyId, List<HerosEngage> heros) {
    public record HerosEngage(UUID id, String nom, UUID joueurId) {}
}