package main.domain.hero.inventaire;


import main.domain.hero.inventaire.item.Item;
import main.erreur.exception.InventairePleinException;
import main.erreur.exception.ObjetNonPossedeException;

import java.util.ArrayList;
import java.util.List;

public class Inventaire {

    private static final int CAPACITE_MAX = 10;
    private List<Item> inventaire = new ArrayList<>();


    public void ajouteItem(Item item) {

        if (item == null) {
            System.out.println("L'item est obligatoire.");
        }

        if (isInventairePlein()) {
            throw new InventairePleinException("Impossible d'ajouter un objet : l'inventaire est plein (maximum " + CAPACITE_MAX + ").");
        }

        inventaire.add(item);
    }


    public void retirerItem(Item item) {
        if (!inventaire.remove(item)) {
            throw new ObjetNonPossedeException("Cet objet n'est pas dans l'inventaire.");
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
        return CAPACITE_MAX;
    }

    public List<Item> getInventaire() {
        return List.copyOf(inventaire);
    }

}
