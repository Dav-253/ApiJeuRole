package Hero.caracteristique;

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

    // TODO:  Les bonus ou pénalités liés à l’espèce (Voir document)

    // TODO: Méthode pour appliquer bonus


    public int getValeur() {
        return valeur;
    }

    // Respecte le calcul (du document): (valeur - 10) / 2  —  résultat arrondi vers le bas
    public int getModificateur() {
        return Math.floorDiv(valeur - 10, 2);
    }

}
