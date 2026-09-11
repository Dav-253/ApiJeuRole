package Hero.inventaire;

import Hero.inventaire.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventaire {

    private final int capaciteMax = 10;
    private List<Item> inventaire = new ArrayList<>();


    public void ajouteItem(Item item) {

        if (isInventairePlein()) {
            System.out.println("Impossible d'ajouter un item, inventaire plein");
            return;
        }

        inventaire.add(item);
    }





    public boolean isInventairePlein() {
        return inventaire.size() == capaciteMax;
    }

}
