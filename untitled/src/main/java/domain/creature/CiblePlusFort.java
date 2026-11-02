// CiblePlusFort.java (Orc : le héros actif avec le plus de PV)
package domain.creature;

import domain.heros.Heros;
import java.util.Comparator;
import java.util.List;

public class CiblePlusFort implements ComportementCiblage {
    @Override
    public Heros choisirCible(List<Heros> herosActifs) {
        return herosActifs.stream()
                .max(Comparator.comparingInt(Heros::getPvActuel))
                .orElseThrow(() -> new IllegalStateException("Aucun héros actif à cibler"));
    }
}