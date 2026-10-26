package partie.infrastructure;

import partie.domain.Partie;
import partie.domain.PartieRepository;
import partie.domain.exception.PartieIntrouvableException;

import java.util.Map;
import java.util.UUID;
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
}
