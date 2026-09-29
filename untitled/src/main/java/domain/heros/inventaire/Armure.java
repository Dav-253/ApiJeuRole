package domain.heros.inventaire;

import domain.heros.inventaire.item.Item;

import java.util.Objects;

public class Armure implements Item {

    private final TypeArmure typeArmure;

    public Armure(TypeArmure typeArmure) {
        this.typeArmure = Objects.requireNonNull(typeArmure, "Le type d'armure est obligatoire.");
    }

    @Override
    public String getNom() {
        return typeArmure.getNom();
    }

    public TypeArmure getType() {
        return typeArmure;
    }

    public boolean isBonusDexteriteApplicable() {
        return getType().getBonusDexteriteApplicable();
    }

    public int getProtectionDeBase() {
        return getType().getProtectionDeBase();
    }

    public int calculerClasseArmure(int modificateurDexterite) {
        return typeArmure.calculerClasseArmure(modificateurDexterite);
    }

}
