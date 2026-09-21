package main.domain.hero;

import main.domain.hero.autre.Nom;
import main.domain.hero.caracteristique.TypeCaracteristique;
import main.domain.hero.classe.ClasseHero;
import main.domain.hero.espece.Espece;

import java.util.Map;
import java.util.UUID;

public class HeroFactory {

    private HeroFactory() {}

    public static Hero creerHero(UUID id, String nomBrut,
                                 Espece espece, ClasseHero classeHero,
                                 Map<TypeCaracteristique, Integer> valeursBrutes) {

        Nom nom = new Nom(nomBrut);


        Map<TypeCaracteristique, ValeurCaracteristique> valeurs = new EnumMap<>(TypeCaracteristique.class);


        return new Hero(id, nom, espece, classeHero, )

    }

}
