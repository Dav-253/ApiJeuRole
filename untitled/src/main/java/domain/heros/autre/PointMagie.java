package domain.heros.autre;

import domain.heros.classe.ClasseHeros;
import erreur.exception.PmInvalidException;

public class PointMagie {

    private int pmActuel;
    private final int pmMax;

    public PointMagie(int pmActuel, int pmMax) {
        if (pmMax <= 0) {
            throw new PmInvalidException("Le PV maximal doit être positif");
        }

        if (pmActuel < 0 || pmActuel > pmMax) {
            throw new PmInvalidException("Le PV actuel doit être compris entre 0 et le maximum");
        }

        this.pmActuel = pmActuel;
        this.pmMax = pmMax;
    }

    // PV maximum au niveau 1 = PV de base de la classe + modificateur de Constitution
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
            throw new PmInvalidException("La quantité de magie dépensée ne peut pas être négative");
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
