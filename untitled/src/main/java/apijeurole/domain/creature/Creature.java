package apijeurole.domain.creature;

import apijeurole.heros.domain.Heros;
import java.util.List;
import java.util.Objects;
import apijeurole.heros.domain.autre.Nom;
import apijeurole.heros.domain.autre.PointVie;
import apijeurole.erreur.exception.CreatureInvalideException;

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
            throw new CreatureInvalideException("La classe d'armure doit être positive.");
        }
        if (xp < 0) {
            throw new CreatureInvalideException("L'XP ne peut pas être négatif.");
        }
        if (nbDesDegats <= 0 || nbFacesDegats <= 0) {
            throw new CreatureInvalideException("Les dégâts doivent être définis par au moins 1 dé.");
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

    public Heros choisirCible(List<Heros> herosActifs) {
        return comportementCiblage.choisirCible(herosActifs);
    }
}