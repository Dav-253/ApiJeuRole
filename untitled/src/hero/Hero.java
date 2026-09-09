package Hero;

import Hero.autre.Niveau;
import Hero.autre.NomHero;
import Hero.autre.PvHero;
import Hero.caracteristique.Caracteristiques;
import Hero.caracteristique.Caracteristiques;

public class Hero {

    private NomHero nom;
    private Niveau niveau;
    private PvHero pvHero;

    // Espece Hero
    // Classe Hero
    private Caracteristiques caracteristiques;
    //  points de magie

    // Un héros conserve son état entre les combats. Ainsi, un héros qui termine un combat blessé ne retrouve pas
    //automatiquement tous ses points de vie avant le combat suivant.

    public Hero(NomHero nom) {
        this.nom = nom;
    }


    public String getNom() {
        return nom.getValue();
    }
}
