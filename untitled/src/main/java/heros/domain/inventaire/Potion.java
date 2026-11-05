package heros.domain.inventaire;

import heros.domain.autre.PointVie;
import heros.domain.inventaire.item.Item;
import util.Des;

public class Potion implements Item {

    private static final int NOMBRE_DES = 2;
    private static final int FACES_DE = 4;
    private static final int BONUS_FIXE = 2;

    @Override
    public String getNom() {
        return "Potion de soin";
    }

    public int soigner(PointVie pv) {
        int montant = Des.lancer(NOMBRE_DES, FACES_DE) + BONUS_FIXE;
        pv.guerrir(montant);
        return montant;
    }

    
}
