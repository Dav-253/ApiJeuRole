package lobby.infrastructure;


import lobby.domain.Lobby;
import lobby.domain.LobbyRepository;
import lobby.domain.exception.LobbyIntrouvableException;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** TODO : stocker un modèle de persistance + mapper plutôt que l'objet du domaine directement. */
public class InMemoryLobbyRepository implements LobbyRepository {

    private final Map<UUID, Lobby> lobbys = new ConcurrentHashMap<>();

    @Override
    public void save(Lobby lobby) {
        lobbys.put(lobby.getId(), lobby);
    }

    @Override
    public Lobby fetchById(UUID id) {
        Lobby lobby = lobbys.get(id);
        if (lobby == null) {
            throw new LobbyIntrouvableException(id);
        }
        return lobby;
    }

    @Override
    public Collection<Lobby> findAll() {
        return List.copyOf(lobbys.values());
    }
}
