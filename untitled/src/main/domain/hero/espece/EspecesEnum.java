package main.domain.hero.espece;

import hero.caracteristique.TypeCaracteristique;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public enum EspecesEnum implements Espece {

    HUMAIN("Humain", Map.of(
            TypeCaracteristique.FORCE, 1,
            TypeCaracteristique.DEXTERITE, 1,
            TypeCaracteristique.CONSTITUTION, 1,
            TypeCaracteristique.INTELLIGENCE, 1,
            TypeCaracteristique.SAGESSE, 1,
            TypeCaracteristique.CHARISME, 1
    )),

    ELFE("Elfe", Map.of(
            TypeCaracteristique.DEXTERITE, 2,
            TypeCaracteristique.INTELLIGENCE, 1
    )),

    NAIN("Nain", Map.of(
            TypeCaracteristique.CONSTITUTION, 2,
            TypeCaracteristique.FORCE, 1
    )),

    ORC("Orc", Map.of(
            TypeCaracteristique.FORCE, 2,
            TypeCaracteristique.CONSTITUTION, 1,
            TypeCaracteristique.INTELLIGENCE, -1
    ));

    private final String nom;
    private final Map<TypeCaracteristique, Integer> modificateurs;

    EspecesEnum(String nom, Map<TypeCaracteristique, Integer> modificateurs) {
        this.nom = nom;
        this.modificateurs = new EnumMap<>(TypeCaracteristique.class);
        this.modificateurs.putAll(modificateurs);
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public Map<TypeCaracteristique, Integer> getModificateursEspece() {
        return Collections.unmodifiableMap(modificateurs);
    }

}
