package main.domain.joueur;

import main.domain.hero.Hero;
import main.domain.hero.autre.Nom;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Joeur {

    private final UUID id;
    private final List<Hero> herosCreer = new ArrayList<>();
    private Hero heroSelectionner;

    public Joeur(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Hero getHeroSelectionner() {
        return heroSelectionner;
    }

    public void setHeroSelectionner(Hero heroSelectionner) {
        this.heroSelectionner = heroSelectionner;
    }

    public List<Hero> getHerosCreer() {
        return herosCreer;
    }

}
