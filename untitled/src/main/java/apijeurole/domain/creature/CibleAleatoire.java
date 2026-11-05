package apijeurole.domain.creature;

import apijeurole.heros.domain.Heros;
import apijeurole.erreur.exception.AucunHerosCiblableException;
import java.util.List;
import java.util.Objects;
import apijeurole.util.LanceurDes;

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