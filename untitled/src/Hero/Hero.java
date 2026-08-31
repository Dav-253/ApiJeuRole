package Hero;

public class Hero {

    private NomHero nom;

    public Hero(NomHero nom) {
        this.nom = nom;
    }


    public NomHero getNom() {
        return nom.getValue();
    }
}
