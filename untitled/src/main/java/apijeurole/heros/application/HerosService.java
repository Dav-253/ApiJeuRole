package apijeurole.heros.application;

import apijeurole.heros.domain.Heros;
import apijeurole.heros.domain.HerosFactory;
import apijeurole.heros.domain.HerosRepository;
import apijeurole.heros.domain.caracteristique.TypeCaracteristique;
import apijeurole.heros.domain.classe.ClasseHeros;
import apijeurole.heros.domain.espece.Espece;
import apijeurole.heros.domain.espece.EspecesEnum;
import apijeurole.heros.domain.exception.EspeceInconnueException;
import apijeurole.heros.domain.inventaire.Arme;
import apijeurole.heros.domain.inventaire.TypeArme;

import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class HerosService {

    private final HerosRepository herosRepository;
    private final HerosFactory herosFactory;

    public HerosService(HerosRepository herosRepository) {
        this.herosRepository = herosRepository;
        this.herosFactory = new HerosFactory(herosRepository);
    }

    public Heros creerHeros(UUID joueurId, String nom, String espece, String classe,
                            Map<String, Integer> caracteristiques) {
        Espece especeChoisie = lireEspece(espece);
        ClasseHeros classeChoisie = lireClasse(classe);

        Heros heros = herosFactory.creer(joueurId, nom, especeChoisie, classeChoisie,
                lireCaracteristiques(caracteristiques), new Arme(armeDeDepart(classeChoisie)));
        herosRepository.sauvegarder(heros);
        return heros;
    }

    private static Espece lireEspece(String espece) {
        try {
            return EspecesEnum.valueOf(espece.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new EspeceInconnueException("Espèce inconnue : " + espece);
        }
    }

    private static ClasseHeros lireClasse(String classe) {
        try {
            return ClasseHeros.valueOf(classe.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Classe inconnue : " + classe);
        }
    }

    private static Map<TypeCaracteristique, Integer> lireCaracteristiques(Map<String, Integer> saisies) {
        Map<TypeCaracteristique, Integer> resultat = new EnumMap<>(TypeCaracteristique.class);
        saisies.forEach((cle, valeur) -> {
            TypeCaracteristique type = trouverType(cle);
            if (valeur == null) {
                throw new IllegalArgumentException("Valeur manquante pour : " + cle);
            }
            resultat.put(type, valeur);
        });
        return resultat;
    }

    private static TypeCaracteristique trouverType(String cle) {
        String normalisee = java.text.Normalizer.normalize(cle, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toUpperCase(Locale.ROOT);
        try {
            return TypeCaracteristique.valueOf(normalisee);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Caractéristique inconnue : " + cle);
        }
    }

    private static TypeArme armeDeDepart(ClasseHeros classe) {
        return switch (classe) {
            case GUERRIER -> TypeArme.EPEE_LONGUE;
            case RODEUR -> TypeArme.ARC;
            case MAGE -> TypeArme.BATON;
            case CLERC -> TypeArme.MASSE;
        };
    }
}
