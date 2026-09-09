package Hero.autre;

public class Niveau {

    private int niveauActuel;
    private int xpTotal;
    private int xpProchainNiveau;


    public Niveau() {
        this.niveauActuel = 0;
        this.xpTotal = 0;
        this.xpProchainNiveau = 100;
    }

    public void ajoutXp(int quantite) {
        if (quantite < 0) return;

        this.xpTotal += quantite;

        while (this.xpTotal >= this.xpProchainNiveau) {
            levelUp();
        }
    }

    private void levelUp() {
        this.niveauActuel++;

        this.xpProchainNiveau = (int) (this.xpProchainNiveau * 1.5);


    }

}
