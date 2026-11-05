package apijeurole.domain.creature;

import apijeurole.heros.domain.Heros;
import apijeurole.erreur.exception.AucunHerosCiblableException;
import java.util.Comparator;
import java.util.List;

public class CiblePlusFort implements ComportementCiblage {
    @Override
    public Heros choisirCible(List<Heros> herosActifs) {
        return herosActifs.stream()
                .max(Comparator.comparingInt(Heros::getPvActuel))
                .orElseThrow(AucunHerosCiblableException::new);
    }
}