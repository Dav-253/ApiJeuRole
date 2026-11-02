package domain.creature;

import domain.heros.Heros;
import java.util.List;

public interface ComportementCiblage {
    Heros choisirCible(List<Heros> herosActifs);
}