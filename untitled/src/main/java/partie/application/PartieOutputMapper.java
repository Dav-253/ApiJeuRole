package partie.application;

import partie.domain.Partie;
import partie.domain.Partie;

import java.util.List;

public class PartieOutputMapper {

    public PartieOutput toOutput(partie.domain.Partie partie) {
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
