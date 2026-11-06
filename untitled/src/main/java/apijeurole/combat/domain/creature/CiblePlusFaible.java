package apijeurole.combat.domain.creature;

import apijeurole.heros.domain.Heros;
import apijeurole.combat.domain.exception.AucunHerosCiblableException;
import java.util.Comparator;
import java.util.List;

public class CiblePlusFaible implements ComportementCiblage {
    @Override
    public Heros choisirCible(List<Heros> herosActifs) {
        return herosActifs.stream()
                .min(Comparator.comparingInt(Heros::getPvActuel))
                .orElseThrow(AucunHerosCiblableException::new);
    }
}