package main.domain.hero.inventaire;

import main.domain.hero.caracteristique.TypeCaracteristique;
import main.domain.hero.inventaire.item.Item;

public class Armure implements Item {
    private final String nom;
    private final int protectionDeBase;
    private final boolean bonusDexteriteApplicable;

    public Armure(String nom, int protectionDeBase, boolean bonusDexteriteApplicable) {
        this.nom = nom;
        this.protectionDeBase = protectionDeBase;
        this.bonusDexteriteApplicable = bonusDexteriteApplicable;
    }

    @Override
    public String getNom() {
        return nom;
    }

    public int getProtectionDeBase() { return protectionDeBase; }

    public boolean isBonusDexteriteApplicable() { return bonusDexteriteApplicable; }

    public int calculerClasseArmure(TypeArmure armure, int modificateurDexterite) {
        if (armure == null) {
            return 10 + modificateurDexterite;
        }
        return bonusDexteriteApplicable
                ? protectionDeBase + modificateurDexterite
                : protectionDeBase;
    }

}
