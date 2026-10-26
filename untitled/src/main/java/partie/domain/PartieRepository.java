package partie.domain;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface PartieRepository {
    void save(Partie partie);
    Partie fetchById(UUID id);
    Set<UUID> herosDejaEnPartieEnCours(Collection<UUID> herosIds);
}
