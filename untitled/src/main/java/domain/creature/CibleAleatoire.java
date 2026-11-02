package domain.creature;

import domain.heros.Heros;
import erreur.exception.AucunHerosCiblableException;
import util.Des;
import java.util.List;

public class CibleAleatoire implements ComportementCiblage {
    @Override
    public Heros choisirCible(List<Heros> herosActifs) {
        if (herosActifs.isEmpty()) {
            throw new AucunHerosCiblableException();
        }
        int index = Des.lancer(1, herosActifs.size()) - 1;
        return herosActifs.get(index);
    }
}