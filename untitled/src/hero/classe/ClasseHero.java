package Hero.classe;

import Hero.caracteristique.TypeCaracteristique;

public enum ClasseHero {

    GUERRIER("Guerrier", 12, 0, TypeCaracteristique.FORCE),
    RODEUR("Rôdeur", 10, 0, TypeCaracteristique.DEXTERITE),
    MAGE("Mage", 6, 10, TypeCaracteristique.INTELLIGENCE),
    CLERC("Clerc", 10, 8, TypeCaracteristique.SAGESSE);

    private final String nom;
    private final int pvDeBase;
    private final int magieDeBase;
    private final TypeCaracteristique aptitudeDominante;

    ClasseHero(String nom, int pvDeBase, int magieDeBase, TypeCaracteristique aptitudeDominante) {
        this.nom = nom;
        this.pvDeBase = pvDeBase;
        this.magieDeBase = magieDeBase;
        this.aptitudeDominante = aptitudeDominante;
    }

    public String getNom() {
        return nom;
    }

    public int getPvDeBase() {
        return pvDeBase;
    }

    public int getMagieDeBase() {
        return magieDeBase;
    }

    public TypeCaracteristique getAptitudeDominante() {
        return aptitudeDominante;
    }

}
