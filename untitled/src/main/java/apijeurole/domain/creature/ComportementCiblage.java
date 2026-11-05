package apijeurole.domain.creature;

import apijeurole.heros.domain.Heros;
import java.util.List;

public interface ComportementCiblage {
    Heros choisirCible(List<Heros> herosActifs);
}