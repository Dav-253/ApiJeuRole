package main.domain.hero.inventaire;

import hero.caracteristique.TypeCaracteristique;
import hero.inventaire.item.Item;

public class Arme implements Item {
    private final String nom;
    private final String degat;
    private final TypeCaracteristique caracteristique;

    public Arme(String nom, String degat, TypeCaracteristique caracteristique) {
        this.nom = nom;
        this.degat = degat;
        this.caracteristique = caracteristique;
    }


    @Override
    public String getNom() {
        return nom;
    }

    public String getDegat() {
        return degat;
    }

    public TypeCaracteristique getCaracteristique() {
        return caracteristique;
    }
}
