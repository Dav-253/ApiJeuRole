package domain.creature;

import heros.domain.Heros;
import java.util.List;

public interface ComportementCiblage {
    Heros choisirCible(List<Heros> herosActifs);
}