package main.domain.joueur;

import main.domain.hero.Hero;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Joeur {

    private UUID id;
    private boolean isReady;

    private final List<Hero> herosCreer = new ArrayList<>();
    private Hero heroSelectionner;

    public Joeur(UUID id, NomJoueur nom) {
        this.id = id;
        this.nom = nom;
    }

    public UUID getId() {
        return id;
    }

    public String getNom() {
        return nom.getValue();
    }

    public boolean isReady() {
        return isReady;
    }

    public Hero getHeroSelectionner() {
        return heroSelectionner;
    }

}
