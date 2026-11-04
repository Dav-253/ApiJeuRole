package heros.domain.autre;

import heros.domain.classe.ClasseHeros;
import heros.domain.exception.PvInvalidException;

public class PointVie {

    private int pvActuel;
    private final int pvMax;

    public PointVie(int pvActuel, int pvMax) {
        if (pvMax <= 0) {
            throw new PvInvalidException("Le PV maximal doit être positif");
        }

        if (pvActuel < 0 || pvActuel > pvMax) {
            throw new PvInvalidException("Le PV actuel doit être compris entre 0 et le maximum");
        }

        this.pvActuel = pvActuel;
        this.pvMax = pvMax;
    }

    // TODO: PV maximum au niveau 1 = PV de base de la classe + modificateur de Constitution
    public static PointVie initial(ClasseHeros classeHero, int modificateurConstitution) {
        int max = Math.max(1, classeHero.getPvDeBase() + modificateurConstitution);
        return new PointVie(max, max);
    }

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
