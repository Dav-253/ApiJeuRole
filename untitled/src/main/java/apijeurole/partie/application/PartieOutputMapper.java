package apijeurole.partie.application;

import apijeurole.partie.domain.Partie;

import java.util.List;

public class PartieOutputMapper {

    public PartieOutput toOutput(Partie partie) {
        return new PartieOutput(
                partie.getId(),
                partie.getLobbyId(),
                partie.getEtat().name(),
                partie.getGroupe().stream()
                        .map(m -> new PartieOutput.Membre(m.joueurId(), m.herosId()))
                        .toList(),
                partie.getCombatActifId(),
                List.copyOf(partie.getRencontresRemportees()));
    }
}
