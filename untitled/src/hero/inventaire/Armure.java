package Hero.inventaire;

import Hero.caracteristique.TypeCaracteristique;
import Hero.inventaire.item.Item;

public class Armure implements Item {
    private final String nom;
    private final String degat;
    private final TypeCaracteristique caracteristique;

    public Armure(String nom, String degat, TypeCaracteristique caracteristique) {
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
