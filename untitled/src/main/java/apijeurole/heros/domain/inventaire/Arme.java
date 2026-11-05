package apijeurole.heros.domain.inventaire;

import apijeurole.heros.domain.caracteristique.TypeCaracteristique;
import apijeurole.heros.domain.inventaire.item.Item;

import java.util.Objects;

public class Arme implements Item {

    private final TypeArme typeArme;

    public Arme(TypeArme typeArme) {
        this.typeArme = Objects.requireNonNull(typeArme, "Le type d'arme est obligatoire.");
    }

    @Override
    public String getNom() {
        return typeArme.getNom();
    }

    public TypeArme getTypeArme() {
        return typeArme;
    }

    public int getNombreDes() {
        return typeArme.getNombreDes();
    }

    public int getFaceDe() {
        return typeArme.getFaceDe();
    }

    public TypeCaracteristique getCaracteristique() {
        return typeArme.getCaracteristique();
    }

}
