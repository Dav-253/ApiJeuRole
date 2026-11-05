package apijeurole.heros.domain.inventaire;

import apijeurole.heros.domain.caracteristique.TypeCaracteristique;

public enum TypeArme {

    EPEE_LONGUE("Épée longue", 1, 8, TypeCaracteristique.FORCE),
    HACHE("Hache", 1, 10, TypeCaracteristique.FORCE),
    MASSE("Masse", 1, 6, TypeCaracteristique.FORCE),
    DAGUE("Dague", 1, 4, TypeCaracteristique.DEXTERITE),
    ARC("Arc", 1, 8, TypeCaracteristique.DEXTERITE),
    BATON("Bâton", 1, 6, TypeCaracteristique.FORCE);

    private final String nom;
    private final int nombreDes;
    private final int faceDe;
    private final TypeCaracteristique caracteristique;

    TypeArme(String nom, int nombreDes, int faceDe, TypeCaracteristique caracteristique) {
        this.nom = nom;
        this.nombreDes = nombreDes;
        this.faceDe = faceDe;
        this.caracteristique = caracteristique;
    }

    public String getNom() {
        return nom;
    }

    public int getNombreDes() {
        return nombreDes;
    }

    public int getFaceDe() {
        return faceDe;
    }

    public TypeCaracteristique getCaracteristique() {
        return caracteristique;
    }

}
