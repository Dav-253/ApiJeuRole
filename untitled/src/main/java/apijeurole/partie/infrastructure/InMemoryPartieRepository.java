package apijeurole.partie.infrastructure;

import apijeurole.partie.domain.EtatPartie;
import apijeurole.partie.domain.Partie;
import apijeurole.partie.domain.PartieRepository;
import apijeurole.partie.domain.exception.PartieIntrouvableException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPartieRepository implements PartieRepository {

    private final Map<UUID, PartiePersistenceModel> parties = new ConcurrentHashMap<>();
    private final PartiePersistenceMapper persistenceMapper;

    public InMemoryPartieRepository(PartiePersistenceMapper persistenceMapper) {
        this.persistenceMapper = persistenceMapper;
    }

    @Override
    public void save(Partie partie) { parties.put(partie.getId(), persistenceMapper.toPersistence(partie)); }

    @Override
    public Partie fetchById(UUID id) {
        PartiePersistenceModel model = parties.get(id);
        if (model == null) {
            throw new PartieIntrouvableException(id);
        }
        return persistenceMapper.toDomain(model);
    }

    @Override
    public Set<UUID> herosDejaEnPartieEnCours(Collection<UUID> herosIds) {
        Set<String> recherches = new HashSet<>();
        herosIds.forEach(id -> recherches.add(id.toString()));

        Set<UUID> trouves = new HashSet<>();
        for (PartiePersistenceModel model : parties.values()) {
            if (EtatPartie.TERMINEE.name().equals(model.etat())) continue;
            for (PartiePersistenceModel.MembreModel membre : model.groupe()) {
                if (recherches.contains(membre.herosId())) {
                    trouves.add(UUID.fromString(membre.herosId()));
                }
            }
        }
        return trouves;
    }
}
