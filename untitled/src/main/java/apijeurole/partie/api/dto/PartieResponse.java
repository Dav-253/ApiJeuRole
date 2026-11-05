package apijeurole.partie.api.dto;

import java.util.List;

public record PartieResponse(
        String id,
        String lobbyId,
        String etat,
        List<Membre> groupe,
        String combatActifId,
        List<String> rencontresRemportees) {

    public record Membre(String joueurId, String herosId) {}
}
