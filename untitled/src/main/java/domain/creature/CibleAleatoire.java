// CibleAleatoire.java (Troll : un héros actif au hasard)
package domain.creature;

import domain.heros.Heros;
import util.Des;
import java.util.List;

public class CibleAleatoire implements ComportementCiblage {
    @Override
    public Heros choisirCible(List<Heros> herosActifs) {
        if (herosActifs.isEmpty()) {
            throw new IllegalStateException("Aucun héros actif à cibler");
        }
        int index = Des.lancer(1, herosActifs.size()) - 1;
        return herosActifs.get(index);
    }
}