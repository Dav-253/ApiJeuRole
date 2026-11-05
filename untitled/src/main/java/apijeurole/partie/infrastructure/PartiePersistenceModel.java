package apijeurole.partie.infrastructure;

import java.util.List;

public record PartiePersistenceModel(
        String id,
        String lobbyId,
        List<MembreModel> groupe,
        String etat,
        String combatActifId,
        String rencontreEnCours,
        List<String> rencontresRemportees
) {

    public record MembreModel(String joueurId, String herosId) {}

    public PartiePersistenceModel {
        groupe = List.copyOf(groupe);
        rencontresRemportees = List.copyOf(rencontresRemportees);
    }
}
