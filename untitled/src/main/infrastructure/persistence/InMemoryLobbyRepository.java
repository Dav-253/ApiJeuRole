package main.infrastructure.persistence;

import main.domain.lobby.Lobby;
import main.domain.lobby.LobbyRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class InMemoryLobbyRepository implements LobbyRepository {

    private final Map<UUID, Lobby> dbSimulator = new ConcurrentHashMap<>();

    @Override
    public void save(Lobby lobby) {
        dbSimulator.put(lobby.getLobbyID(), lobby);
    }

    @Override
    public Optional<Lobby> findById(UUID id) {
        return Optional.ofNullable(dbSimulator.get(id));
    }

    @Override
    public Collection<Lobby> findAll() {
        return dbSimulator.values();
    }

    @Override
    public void deleteById(UUID id) {
        dbSimulator.remove(id);
    }
}
