package domain.heros;

import domain.heros.autre.Nom;
import domain.heros.caracteristique.TypeCaracteristique;
import domain.heros.caracteristique.ValeurCaracteristique;
import domain.heros.classe.ClasseHeros;
import domain.heros.espece.Espece;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

public class HerosFactory {

    private HerosFactory() {}

    public static Heros creerHero(UUID id, String nomBrut,
                                  Espece espece, ClasseHeros classeHero,
                                  Map<TypeCaracteristique, Integer> valeursBrutes) {

        Nom nom = new Nom(nomBrut);


        Map<TypeCaracteristique, ValeurCaracteristique> valeurs = new EnumMap<>(TypeCaracteristique.class);


        return new Heros(id, nom, espece, classeHero, )

    }

}
