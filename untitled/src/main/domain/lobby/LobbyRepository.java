package main.domain.lobby;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface LobbyRepository {
    void save(Lobby lobby);
    Optional<Lobby> findById(UUID id);
    Collection<Lobby> findAll();
    void deleteById(UUID id);
}
