package apijeurole.combat.domain.creature;

import apijeurole.heros.domain.Heros;
import apijeurole.combat.domain.exception.AucunHerosCiblableException;
import java.util.List;
import java.util.Objects;
import apijeurole.commun.util.LanceurDes;

public class CibleAleatoire implements ComportementCiblage {

    private final LanceurDes des;

    public CibleAleatoire(LanceurDes des) {
        this.des = Objects.requireNonNull(des, "Le lanceur de dés est obligatoire.");
    }

    @Override
    public Heros choisirCible(List<Heros> herosActifs) {
        if (herosActifs.isEmpty()) {
            throw new AucunHerosCiblableException();
        }
        int index = des.lancer(1, herosActifs.size()) - 1;
        return herosActifs.get(index);
    }
}