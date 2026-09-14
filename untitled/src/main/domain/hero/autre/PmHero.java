package main.domain.hero.autre;

import main.erreur.exception.PmInvalidException;

public class PmHero {

    private int pmActuel;
    private final int pmMax;

    public PmHero(int pmActuel, int pmMax) {
        if (pmMax <= 0) {
            throw new PmInvalidException("Le PV maximal doit être positif");
        }

        if (pmActuel < 0 || pmActuel > pmMax) {
            throw new PmInvalidException("Le PV actuel doit être compris entre 0 et le maximum");
        }

        this.pmActuel = pmActuel;
        this.pmMax = pmMax;
    }




    // TODO: PV maximum au niveau 1 = PV de base de la classe + modificateur de Constitution


    public int getPmActuel() {
        return pmActuel;
    }

    public int getPmMax() {
        return pmMax;
    }




}
