package main.domain.hero;

import main.domain.hero.autre.Niveau;
import main.domain.hero.autre.PvHero;
import main.domain.hero.caracteristique.Caracteristiques;
import main.domain.hero.autre.Nom;

public class Hero {

    private Nom nom;
    private Niveau niveau;
    private PvHero pvHero;

    // Espece Hero
    // Classe Hero
    private Caracteristiques caracteristiques;
    //  points de magie

    // Un héros conserve son état entre les combats. Ainsi, un héros qui termine un combat blessé ne retrouve pas
    //automatiquement tous ses points de vie avant le combat suivant.

    public String getNom() {
        return nom.getValue();
    }
}
