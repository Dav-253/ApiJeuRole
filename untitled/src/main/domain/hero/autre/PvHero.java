package main.domain.hero.autre;

import main.erreur.exception.PvInvalidException;

public class PvHero {

    private int pvActuel;
    private final int pvMax;

    public PvHero(int pvActuel, int pvMax) {
        if (pvMax <= 0) {
            throw new PvInvalidException("Le PV maximal doit être positif");
        }

        if (pvActuel < 0 || pvActuel > pvMax) {
            throw new PvInvalidException("Le PV actuel doit être compris entre 0 et le maximum");
        }

        this.pvActuel = pvActuel;
        this.pvMax = pvActuel;
    }

    // TODO: PV maximum au niveau 1 = PV de base de la classe + modificateur de Constitution


    public int getPvActuel() {
        return pvActuel;
    }

    public int getPvMax() {
        return pvMax;
    }

    public boolean estHorsCombat() {
        return pvActuel == 0;
    }

    public boolean peutAgir() {
        return !estHorsCombat();
    }


    public void subirDegat(int quantite) {
        if (quantite <= 0) return;
        pvActuel = Math.max(0, pvActuel - quantite);
    }

    public void guerrir(int quantite) {
        if (quantite <= 0) return;

        pvActuel = Math.min(pvMax, pvActuel + quantite);
    }

}
