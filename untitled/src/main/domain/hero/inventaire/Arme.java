package main.domain.hero.inventaire;

import main.domain.hero.caracteristique.TypeCaracteristique;
import main.domain.hero.inventaire.item.Item;

public class Arme implements Item {

    private String nom;
    private int nombreDes;
    private int faceDe;
    private TypeCaracteristique caracteristique;

    public Arme(String nom, int nombreDes, int faceDe, TypeCaracteristique caracteristique) {
        this.nom = nom;
        this.nombreDes = nombreDes;
        this.faceDe = faceDe;
        this.caracteristique = caracteristique;
    }

    @Override
    public String getNom() {
        return nom;
    }

    public int getNombreDes() { return nombreDes; }

    public int getFaceDe() { return faceDe; }

    public TypeCaracteristique getCaracteristique() {
        return caracteristique;
    }

}
