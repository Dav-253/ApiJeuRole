package apijeurole.domain.joueur;

import apijeurole.heros.domain.Heros;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Joeur {

    private final UUID id;
    private final List<Heros> herosCreer = new ArrayList<>();
    private Heros herosSelectionner;

    public Joeur(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Heros getHerosSelectionner() {
        return herosSelectionner;
    }

    public void setHerosSelectionner(Heros herosSelectionner) {
        this.herosSelectionner = herosSelectionner;
    }

    public List<Heros> getHerosCreer() {
        return herosCreer;
    }

}
