package apijeurole.partie.infrastructure;

import apijeurole.partie.domain.EtatPartie;
import apijeurole.partie.domain.MembreGroupe;
import apijeurole.partie.domain.Partie;

import java.util.List;
import java.util.UUID;

public class PartiePersistenceMapper {

    public PartiePersistenceModel toPersistence(Partie partie) {
        return new PartiePersistenceModel(
                partie.getId().toString(),
                partie.getLobbyId().toString(),
                partie.getGroupe().stream()
                        .map(m -> new PartiePersistenceModel.MembreModel(
                                m.joueurId().toString(), m.herosId().toString()))
                        .toList(),
                partie.getEtat().name(),
                partie.getCombatActifId() == null ? null : partie.getCombatActifId().toString(),
                partie.getRencontreEnCours(),
                List.copyOf(partie.getRencontresRemportees()));
    }

    public Partie toDomain(PartiePersistenceModel model) {
        return new Partie(
                UUID.fromString(model.id()),
                UUID.fromString(model.lobbyId()),
                model.groupe().stream()
                        .map(m -> new MembreGroupe(UUID.fromString(m.joueurId()), UUID.fromString(m.herosId())))
                        .toList(),
                EtatPartie.valueOf(model.etat()),
                model.combatActifId() == null ? null : UUID.fromString(model.combatActifId()),
                model.rencontreEnCours(),
                model.rencontresRemportees());
    }
}
