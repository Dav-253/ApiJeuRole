package Hero.autre;

import exception.PvInvalidException;

public class PmHero {

    private int pmActuel;
    private final int pmMax;

    public PmHero(int pmActuel, int pmMax) {
        if (pmMax <= 0) {
            throw new PvInvalidException("Le PV maximal doit être positif");
        }

        if (pmActuel < 0 || pmActuel > pmMax) {
            throw new PvInvalidException("Le PV actuel doit être compris entre 0 et le maximum");
        }

        this.pmActuel = pmActuel;
        this.pmMax = pmActuel;
    }

    // TODO: PV maximum au niveau 1 = PV de base de la classe + modificateur de Constitution


    public int getPmActuel() {
        return pmActuel;
    }

    public int getPmMax() {
        return pmMax;
    }

    public boolean estHorsCombat() {
        return pmActuel == 0;
    }

    public boolean peutAgir() {
        return !estHorsCombat();
    }


    public void subirDegat(int quantite) {
        if (quantite <= 0) return;
        pmActuel = Math.max(0, pmActuel - quantite);
    }

    public void guerrir(int quantite) {
        if (quantite <= 0) return;

        pvActuel = Math.min(pvMax, pvActuel + quantite);
    }

}
