// CiblePlusFaible.java (Gobelin : le héros actif avec le moins de PV)
package domain.creature;

import domain.hero.Hero;
import java.util.Comparator;
import java.util.List;

public class CiblePlusFaible implements ComportementCiblage {
    @Override
    public Hero choisirCible(List<Hero> herosActifs) {
        return herosActifs.stream()
                .min(Comparator.comparingInt(Hero::getPvActuel))
                .orElseThrow(() -> new IllegalStateException("Aucun héros actif à cibler"));
    }
}