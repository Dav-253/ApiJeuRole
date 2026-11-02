package domain.heros.espece;

import domain.heros.caracteristique.TypeCaracteristique;
import erreur.exception.EspeceInconnueException;

import java.util.Map;

/**
 * Catalogue des espèces connues.
 * ATTENTION : seul l'Orc (-1 INT) vient du ticket ; les autres bonus sont des exemples à ajuster.
 * Pour ajouter une espèce : ajouter une constante ici, rien d'autre à modifier.
 */
public enum Especes implements Espece {

    HUMAIN("Humain", Map.of()),
    ORC("Orc", Map.of(TypeCaracteristique.INTELLIGENCE, -1)),
    ELFE("Elfe", Map.of(TypeCaracteristique.DEXTERITE, 1)),
    NAIN("Nain", Map.of(TypeCaracteristique.CONSTITUTION, 1));

    private final String nom;
    private final Map<TypeCaracteristique, Integer> modificateurs;

    Especes(String nom, Map<TypeCaracteristique, Integer> modificateurs) {
        this.nom = nom;
        this.modificateurs = modificateurs;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public Map<TypeCaracteristique, Integer> getModificateursEspece() {
        return modificateurs;
    }

    /** Retrouve une espèce par son nom (insensible à la casse). */
    public static Especes parNom(String texte) {
        for (Especes e : values()) {
            if (e.name().equalsIgnoreCase(texte) || e.nom.equalsIgnoreCase(texte)) {
                return e;
            }
        }
        throw new EspeceInconnueException("Espèce inconnue : « " + texte + " ».");
    }
}