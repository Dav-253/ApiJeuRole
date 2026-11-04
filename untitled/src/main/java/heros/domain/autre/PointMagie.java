package heros.domain.autre;

import heros.domain.classe.ClasseHeros;
import heros.domain.exception.PmInvalidException;

public class PointMagie {

    private int pmActuel;
    private final int pmMax;

    public PointMagie(int pmActuel, int pmMax) {
        // Un max de 0 est valide : Guerrier et Rôdeur n'utilisent pas la magie
        if (pmMax < 0) {
            throw new PmInvalidException("Le PM maximal ne peut pas être négatif");
        }

        if (pmActuel < 0 || pmActuel > pmMax) {
            throw new PmInvalidException("Le PM actuel doit être compris entre 0 et le maximum");
        }

        this.pmActuel = pmActuel;
        this.pmMax = pmMax;
    }

    // PM maximum au niveau 1 = PM de base de la classe, PM au maximum
    public static PointMagie initial(ClasseHeros classeHero) {
        int max = Math.max(0, classeHero.getMagieDeBase());
        return new PointMagie(max, max);
    }

    public int getPmActuel() {
        return pmActuel;
    }

    public int getPmMax() {
        return pmMax;
    }

    public boolean depenser(int quantite) {
        if (quantite <= 0) {
            throw new PmInvalidException("La quantité de magie dépensée doit être positive");
        }

        if (quantite > pmActuel) {
            return false;
        }

        pmActuel -= quantite;
        return true;
    }

    public void restaurer(int quantite) {
        if (quantite <= 0) return;
        pmActuel = Math.min(pmMax, pmActuel + quantite);
    }

    public void restaurerTout() {
        pmActuel = pmMax;
    }
}