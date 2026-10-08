package domain.creature;

import domain.hero.Hero;
import java.util.List;
import java.util.Objects;
import domain.hero.autre.Nom;
import domain.hero.autre.PointVie;
import erreur.exception.PvInvalidException;

public class Creature {

    private final Nom nom;
    private final PointVie pointVie;
    private final int classeArmure;
    private final int bonusInitiative;
    private final int xp;

    private final int attaqueBonus;
    private final int nbDesDegats;
    private final int nbFacesDegats;
    private final int degatsFixe;
    private final ComportementCiblage comportementCiblage;

    public Creature(String nomBrut, int pvMax, int classeArmure, int bonusInitiative, int xp,
        int attaqueBonus, int nbDesDegats, int nbFacesDegats, int degatsFixe,
            ComportementCiblage comportementCiblage) {

        this.nom = new Nom(nomBrut);
        this.pointVie = new PointVie(pvMax, pvMax);

        if (classeArmure <= 0) {
            throw new PvInvalidException("La classe d'armure doit être positive");
        }
        if (xp < 0) {
            throw new PvInvalidException("L'XP ne peut pas être négatif");
        }
        if (nbDesDegats <= 0 || nbFacesDegats <= 0) {
            throw new PvInvalidException("Les dégâts doivent être définis par au moins 1 dé");
        }

        this.classeArmure = classeArmure;
        this.bonusInitiative = bonusInitiative;
        this.xp = xp;
        this.attaqueBonus = attaqueBonus;
        this.nbDesDegats = nbDesDegats;
        this.nbFacesDegats = nbFacesDegats;
        this.degatsFixe = degatsFixe;
        this.comportementCiblage = Objects.requireNonNull(comportementCiblage,
        "Le comportement de ciblage est obligatoire.");
    }

    public String getNom() {
        return nom.getValue();
    }

    public int getPvActuel() {
        return pointVie.getPvActuel();
    }

    public int getClasseArmure() {
        return classeArmure;
    }

    public int getBonusInitiative() {
        return bonusInitiative;
    }

    public int getXp() {
        return xp;
    }

    public boolean estVaincue() {
        return pointVie.estHorsCombat();
    }

    public void subirDegat(int quantite) {
        pointVie.subirDegat(quantite);
    }
        public Hero choisirCible(List<Hero> herosActifs) {
        return comportementCiblage.choisirCible(herosActifs);
    }
}