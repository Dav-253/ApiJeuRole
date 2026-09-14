package main.domain.hero.inventaire;


import main.domain.hero.inventaire.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventaire {

    private final int capaciteMax = 10;
    private List<Item> inventaire = new ArrayList<>();


    public void ajouteItem(Item item) {

        if (item == null) {
            System.out.println("L'item est obligatoire.");
        }


        if (isInventairePlein()) {
            System.out.println("Impossible d'ajouter un objet : l'inventaire est plein (maximum " + capaciteMax + ").");
            return;
        }

        inventaire.add(item);
    }


    public void retirerItem(Item item) {
        if (!inventaire.remove(item)) {
            System.out.println("Cet objet n'est pas dans l'inventaire.");
            return;
        }
    }


    public boolean possedeObjet(Item item) {
        return inventaire.contains(item);
    }

    public boolean isInventairePlein() {
        return inventaire.size() >= capaciteMax;
    }

    public int getNombreObjets() {
        return inventaire.size();
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public List<Item> getInventaire() {
        return List.copyOf(inventaire);
    }

}
