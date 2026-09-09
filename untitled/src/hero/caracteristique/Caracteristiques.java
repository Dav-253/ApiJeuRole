package Hero.caracteristique;

import Hero.caracteristique.TypeCaracteristique;
import Hero.caracteristique.ValeurCaracteristique;

import java.util.EnumMap;
import java.util.Map;

public class Caracteristiques {

    private final Map<TypeCaracteristique, ValeurCaracteristique> caracteristiques = new EnumMap<>(TypeCaracteristique.class);
    // La clé du map est le 'TypeCaracteristique' et la valeur est 'ValeurCaracteristique'

    public Caracteristiques(Map<TypeCaracteristique, ValeurCaracteristique> valeurs) {

    }

    // TODO: Méthode appliquer bonus espece

    public int getValeur(TypeCaracteristique type) {
        return caracteristiques.get(type).getValeur();
    }

    public int getModificateur(TypeCaracteristique type) {
        return caracteristiques.get(type).getModificateur();
    }

}
