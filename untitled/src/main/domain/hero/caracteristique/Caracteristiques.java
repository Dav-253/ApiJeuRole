package main.domain.hero.caracteristique;

import main.domain.hero.espece.Espece;

import java.util.EnumMap;
import java.util.Map;

public class Caracteristiques {

    private final Map<TypeCaracteristique, ValeurCaracteristique> caracteristiques = new EnumMap<>(TypeCaracteristique.class);
    // La clé du map est le 'TypeCaracteristique' et la valeur est 'ValeurCaracteristique'

    public Caracteristiques(Map<TypeCaracteristique, ValeurCaracteristique> valeurs) {

        // Vérification Toutes les caractéristiques doivent être fournies
        if (valeurs == null || valeurs.size() != TypeCaracteristique.values().length) {
            throw new IllegalArgumentException("Les six caractéristiques doivent être fournies.");
        }

        this.caracteristiques.putAll(valeurs);
    }

    
    public Caracteristiques appliquerBonusEspece(Espece espece) {
        Map<TypeCaracteristique, Integer> bonus = espece.getModificateursEspece();
        Map<TypeCaracteristique, ValeurCaracteristique> resultat = new EnumMap<>(TypeCaracteristique.class);

        for (TypeCaracteristique type : TypeCaracteristique.values()) {
            int bonusPourType = bonus.getOrDefault(type, 0);
            resultat.put(type, caracteristiques.get(type).appliquerBonus(bonusPourType));
        }

        return new Caracteristiques(resultat);
    }

    public int getValeur(TypeCaracteristique type) {
        return caracteristiques.get(type).getValeur();
    }

    public int getModificateur(TypeCaracteristique type) {
        return caracteristiques.get(type).getModificateur();
    }

}
