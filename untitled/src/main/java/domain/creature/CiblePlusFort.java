// CiblePlusFort.java (Orc : le héros actif avec le plus de PV)
package domain.creature;

import domain.hero.Hero;
import java.util.Comparator;
import java.util.List;

public class CiblePlusFort implements ComportementCiblage {
    @Override
    public Hero choisirCible(List<Hero> herosActifs) {
        return herosActifs.stream()
                .max(Comparator.comparingInt(Hero::getPvActuel))
                .orElseThrow(() -> new IllegalStateException("Aucun héros actif à cibler"));
    }
}