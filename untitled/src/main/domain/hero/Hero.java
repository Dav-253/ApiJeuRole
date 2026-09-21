package main.domain.hero;

import main.domain.hero.autre.Niveau;
import main.domain.hero.autre.PointMagie;
import main.domain.hero.autre.PointVie;
import main.domain.hero.caracteristique.Caracteristiques;
import main.domain.hero.autre.Nom;
import main.domain.hero.caracteristique.TypeCaracteristique;
import main.domain.hero.classe.ClasseHero;
import main.domain.hero.espece.Espece;
import main.domain.hero.inventaire.*;
import main.erreur.exception.EquipementInvalideException;
import main.erreur.exception.ObjetNonPossedeException;

import java.util.Objects;
import java.util.UUID;

public class Hero {

    private final UUID id;
    private final UUID proprietaireId;

    private Nom nom;

    private final Espece espece;
    private final ClasseHero classeHero;
    private final Caracteristiques caracteristiques;


    private final Niveau niveau;
    private final PointVie pvHero;
    private final PointMagie pmHero;

    private final Inventaire inventaire = new Inventaire();
    private Arme armeEquipee;
    private Armure armureEquipee;

    public Hero(UUID proprietaireId, Nom nom, Espece espece,
                ClasseHero classeHero, Caracteristiques caracteristiques,
                Niveau niveau, PointVie pvHero, PointMagie pmHero) {
        this.id = UUID.randomUUID();
        this.proprietaireId = Objects.requireNonNull(proprietaireId, "Le propriétaire est obligatoire.");
        this.nom = Objects.requireNonNull(nom, "Le nom est obligatoire.");
        this.espece = Objects.requireNonNull(espece, "L'espèce est obligatoire.");
        this.classeHero = Objects.requireNonNull(classeHero, "La classe est obligatoire.");
        this.caracteristiques = Objects.requireNonNull(caracteristiques, "Les caractéristiques sont obligatoires.");
        this.pvHero = Objects.requireNonNull(pvHero, "Les PV sont obligatoires.");
        this.pmHero = Objects.requireNonNull(pmHero, "Les PM sont obligatoires.");
        this.niveau = new Niveau();
    }



    public UUID getId() { return id; }

    public UUID getProprietaireId() { return proprietaireId; }

    public String getNom() {
        return nom.getValue();
    }



    public Espece getEspece() { return espece; }

    public ClasseHero getClasseHero() { return classeHero; }

    public int getCaracteristiques(TypeCaracteristique type) {
        return caracteristiques.getValeur(type);
    }

    public int getModificateur(TypeCaracteristique type) {
        return caracteristiques.getModificateur(type);
    }



    public int getNiveauActuel() { return niveau.getNiveauActuel(); }

    public int getExperience() { return niveau.getXpTotal(); }



    public int getPvActuel() {
        return pvHero.getPvActuel();
    }

    public int getPvMax() { return pvHero.getPvMax(); }

    public int getPmActuel() {
        return pmHero.getPmActuel();
    }

    public int getPmMax() { return pmHero.getPmMax(); }

    public boolean estHorsCombat() { return pvHero.estHorsCombat(); }

    public void subirDegat(int quantite) { pvHero.subirDegat(quantite); }

    public void guerrir(int quantite) { pvHero.guerrir(quantite); }

    /** @return true si la dépense de magie a pu être faite, false si le héros n'a pas assez de PM. */
    public boolean depenserMagie(int quantite) { return pmHero.depenser(quantite); }



    public Inventaire getInventaire() {
        return inventaire;
    }

    public Arme getArmeEquipee() {
        return armeEquipee;
    }

    public Armure getArmureEquipee() {
        return armureEquipee;
    }


    public void equiperArme(Arme arme) {
        if (arme == null) {
            System.out.println("L'arme est obligatoire");
            return;
        }

        if (!inventaire.possedeObjet(arme)) {
            throw new ObjetNonPossedeException("Le héros ne possède pas cette arme.");
        }
        if (!classeHero.peutUtiliser(arme.getType())) {
            throw new EquipementInvalideException(
                    "La classe " + classeHero.getNom() + " ne peut pas utiliser : " + arme.getNom());
        }
        this.armeEquipee = arme;
    }

    public void equiperArmure(Armure armure) {
        if (armure == null) {
            System.out.println("L'armure est obligatoire");
            return;
        }

        if (!inventaire.possedeObjet(armure)) {
            throw new ObjetNonPossedeException("Le héros ne possède pas cette armure.");
        }

        if (!classeHero.peutPorter(armure.getType())) {
            throw new EquipementInvalideException("La classe " + classeHero.getNom() + " ne peut pas porter : " + armure.getNom());
        }
        this.armureEquipee = armure;
    }

    public int getClasseArmure() {
        int modDex = getModificateur(TypeCaracteristique.DEXTERITE);
        Armure typeEquipe = (armureEquipee == null) ? null : armureEquipee.getType();
        return TypeArmure.calculerCA(typeEquipe, modDex);
    }

    public int utiliserPotion(Potion potion) {
        if (potion == null) {
            System.out.println("La potion est obligatoire");
            return -1;
        }

        if (!inventaire.possedeObjet(potion)) {
            throw new ObjetNonPossedeException("Le héros ne possède pas cette potion.");
        }
        int montantSoigne = potion.soigner(pvHero);
        inventaire.retirerItem(potion);
        return montantSoigne;
    }


    @Override
    public String toString() {
        return "Hero{" + getNom() + ", " + espece.getNom() + "/" + classeHero.getNom()
                + ", niveau " + getNiveauActuel() + "}";

    }
}
