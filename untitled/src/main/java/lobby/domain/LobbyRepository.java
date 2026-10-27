package lobby.domain;

import java.util.Collection;
import java.util.UUID;

public interface LobbyRepository {
    void save(Lobby lobby);

    /** @throws lobby.domain.exception.LobbyIntrouvableException si le lobby n'existe pas */
    Lobby fetchById(UUID id);

    Collection<Lobby> findAll();
}
