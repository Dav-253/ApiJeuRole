package heros.domain;

import heros.domain.autre.Niveau;
import heros.domain.autre.Nom;
import heros.domain.autre.PointMagie;
import heros.domain.autre.PointVie;
import heros.domain.caracteristique.Caracteristiques;
import heros.domain.caracteristique.TypeCaracteristique;
import heros.domain.caracteristique.ValeurCaracteristique;
import heros.domain.classe.ClasseHeros;
import heros.domain.exception.NomDejaUtiliseException;
import heros.domain.espece.Espece;
import heros.domain.inventaire.Arme;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

public class HerosFactory {

    /** À implémenter par ton dépôt : sert à vérifier l'unicité du nom. */
    public interface NomRepository {
        boolean nomExiste(Nom nom);
    }

    private final NomRepository repository;

    public HerosFactory(NomRepository repository) {
        this.repository = repository;
    }

    public Heros creer(UUID proprietaireId, String nomSaisi, Espece espece, ClasseHeros classe,
                       Map<TypeCaracteristique, Integer> saisies, Arme armeDepart) {

        // Nom : format validé par Nom, unicité validée ici
        Nom nom = new Nom(nomSaisi);
        if (repository.nomExiste(nom)) {
            throw new NomDejaUtiliseException("Ce nom est déjà utilisé.");
        }

        // Les six caractéristiques sont obligatoires ; ValeurCaracteristique valide 3-18
        Map<TypeCaracteristique, ValeurCaracteristique> valeurs = new EnumMap<>(TypeCaracteristique.class);
        for (TypeCaracteristique type : TypeCaracteristique.values()) {
            Integer saisie = saisies.get(type);
            if (saisie == null) {
                throw new IllegalArgumentException("Caractéristique manquante : " + type);
            }
            valeurs.put(type, new ValeurCaracteristique(saisie));
        }

        // Bonus d'espèce appliqués ensuite, plafond à 20 (géré par ValeurCaracteristique)
        Caracteristiques caracteristiques = new Caracteristiques(valeurs).appliquerBonusEspece(espece);

        // PV max niveau 1 = PV de base + mod CON (minimum 1) ; PM selon la classe ; les deux au maximum
        int modCon = caracteristiques.getModificateur(TypeCaracteristique.CONSTITUTION);
        PointVie pv = PointVie.initial(classe, modCon);
        PointMagie pm = PointMagie.initial(classe);

        // Niveau 1, 0 XP
        return new Heros(proprietaireId, nom, espece, classe, caracteristiques,
                new Niveau(), pv, pm, armeDepart);
    }
}