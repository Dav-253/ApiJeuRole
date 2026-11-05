package heros.domain.caracteristique;

public class ValeurCaracteristique {

    public static final int MIN_CREATION = 3;
    public static final int MAX_CREATION = 18;
    public static final int MAX_CARACTERISTIQUE = 20;

    private final int valeur;

    public ValeurCaracteristique(int valeur) {

        // Vérification si valeur intiale est entre 3 et 18
        if (valeur < MIN_CREATION || valeur > MAX_CREATION) {
            throw new IllegalArgumentException("La valeur initiale doit être entre" + MIN_CREATION + " et " + MAX_CREATION);
        }

        this.valeur = valeur;
    }

    /** Constructeur interne, utilisé uniquement pour produire la valeur finale après bonus d'espèce. */
    private ValeurCaracteristique(int valeurFinale, boolean interne) { this.valeur = valeurFinale; }


    /**
     * Applique le bonus (ou la pénalité) d'espèce sur cette valeur et retourne
     * une nouvelle instance. La borne de création (3-18) ne s'applique plus ici :
     * seule la borne absolue de 20 (doc §3) est respectée.
     */
    public ValeurCaracteristique appliquerBonus(int bonus) {
        int valeur = Math.min(MAX_CARACTERISTIQUE, this.valeur + bonus);
        return new ValeurCaracteristique(valeur, true);
    }


    public int getValeur() {
        return valeur;
    }

    public int getModificateur() {
        return Math.floorDiv(valeur - 10, 2);
    }

}
