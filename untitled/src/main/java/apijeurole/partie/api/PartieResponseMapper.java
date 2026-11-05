package apijeurole.partie.api;

import apijeurole.partie.api.dto.PartieResponse;
import apijeurole.partie.application.PartieOutput;

public class PartieResponseMapper {

    public PartieResponse toResponse(PartieOutput partie) {
        return new PartieResponse(
                partie.id().toString(),
                partie.lobbyId().toString(),
                partie.etat(),
                partie.groupe().stream()
                        .map(m -> new PartieResponse.Membre(m.joueurId().toString(), m.herosId().toString()))
                        .toList(),
                partie.combatActifId() == null ? null : partie.combatActifId().toString(),
                partie.rencontresRemportees()
        );
    }
}
