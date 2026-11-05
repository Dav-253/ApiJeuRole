package apijeurole.heros.domain.classe;

import apijeurole.heros.domain.caracteristique.TypeCaracteristique;
import apijeurole.heros.domain.inventaire.TypeArme;
import apijeurole.heros.domain.inventaire.TypeArmure;

import java.util.EnumSet;
import java.util.Set;

public enum ClasseHeros {

    GUERRIER("Guerrier", 12, 0, TypeCaracteristique.FORCE,
            EnumSet.of(TypeArme.EPEE_LONGUE, TypeArme.HACHE, TypeArme.MASSE, TypeArme.DAGUE, TypeArme.BATON),
            EnumSet.of(TypeArmure.CUIR, TypeArmure.MAILLES)),

    RODEUR("Rôdeur", 10, 0, TypeCaracteristique.DEXTERITE,
            EnumSet.of(TypeArme.ARC, TypeArme.DAGUE, TypeArme.EPEE_LONGUE),
            EnumSet.of(TypeArmure.CUIR)),

    MAGE("Mage", 6, 10, TypeCaracteristique.INTELLIGENCE,
            EnumSet.of(TypeArme.BATON, TypeArme.DAGUE),
            EnumSet.noneOf(TypeArmure.class)),

    CLERC("Clerc", 10, 8, TypeCaracteristique.SAGESSE,
            EnumSet.of(TypeArme.MASSE, TypeArme.BATON),
            EnumSet.of(TypeArmure.CUIR, TypeArmure.MAILLES));

    private final String nom;
    private final int pvDeBase;
    private final int magieDeBase;
    private final TypeCaracteristique aptitudeDominante;
    private final Set<TypeArme> armesAutorisees;
    private final Set<TypeArmure> armuresAutorisees;

    ClasseHeros(String nom, int pvDeBase, int magieDeBase, TypeCaracteristique aptitudeDominante,
                Set<TypeArme> armesAutorisees, Set<TypeArmure> armuresAutorisees) {
        this.nom = nom;
        this.pvDeBase = pvDeBase;
        this.magieDeBase = magieDeBase;
        this.aptitudeDominante = aptitudeDominante;
        this.armesAutorisees = armesAutorisees;
        this.armuresAutorisees = armuresAutorisees;
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

    public boolean peutUtiliser(TypeArme arme) {
        return armesAutorisees.contains(arme);
    }

    public boolean peutPorter(TypeArmure armure) {
        return armuresAutorisees.contains(armure);
    }

}
