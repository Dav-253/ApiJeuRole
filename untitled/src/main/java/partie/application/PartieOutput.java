package partie.application;

import java.util.List;
import java.util.UUID;

public record PartieOutput(
        UUID id,
        UUID lobbyId,
        String etat,
        List<Membre> groupe,
        UUID combatActifId,
        List<String> rencontresRemportees
) {

    public record Membre(UUID joueurId, UUID herosId) {}
}

