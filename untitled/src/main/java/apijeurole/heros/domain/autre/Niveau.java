package apijeurole.heros.domain.autre;

public class Niveau {

    private static final int[] XP_NIVEAUX = {0, 300, 900, 2700, 6500};
    private static final int NIVEAU_MAX = 5;

    private int niveauActuel;
    private int xpTotal;


    public Niveau() {
        this.niveauActuel = 1;
        this.xpTotal = 0;
    }


    public int getNiveauActuel() { return niveauActuel; }

    public int getXpTotal() { return xpTotal; }

    public boolean estNiveauMax() { return NIVEAU_MAX == this.niveauActuel; }

    public void ajoutXp(int quantite) {
        if (quantite < 0) {
            throw new IllegalArgumentException("L'expérience gagnée ne peut pas être négative");
        }

        this.xpTotal += quantite;

        while (!estNiveauMax() && xpTotal >= XP_NIVEAUX[niveauActuel]) {
            niveauActuel++;
        }
        return;
    }
}
