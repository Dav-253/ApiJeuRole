package partie.domain;

import java.util.UUID;

public interface PartieRepository {
    void save(Partie partie);
    Partie fetchById(UUID id);
}
