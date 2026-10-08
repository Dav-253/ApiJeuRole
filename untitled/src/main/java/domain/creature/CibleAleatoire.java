// CibleAleatoire.java (Troll : un héros actif au hasard)
package domain.creature;

import domain.hero.Hero;
import util.Des;
import java.util.List;

public class CibleAleatoire implements ComportementCiblage {
    @Override
    public Hero choisirCible(List<Hero> herosActifs) {
        if (herosActifs.isEmpty()) {
            throw new IllegalStateException("Aucun héros actif à cibler");
        }
        int index = Des.lancer(1, herosActifs.size()) - 1;
        return herosActifs.get(index);
    }
}