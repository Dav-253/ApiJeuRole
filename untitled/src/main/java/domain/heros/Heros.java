package domain.heros;

import domain.heros.autre.Niveau;
import domain.heros.autre.PointMagie;
import domain.heros.autre.PointVie;
import domain.heros.caracteristique.Caracteristiques;
import domain.heros.autre.Nom;
import domain.heros.caracteristique.TypeCaracteristique;
import domain.heros.classe.ClasseHeros;
import domain.heros.espece.Espece;
import domain.heros.inventaire.*;
import erreur.exception.EquipementInvalideException;
import erreur.exception.ObjetNonPossedeException;

import java.util.Objects;
import java.util.UUID;

public class Heros {

    private final UUID id;
    private final UUID proprietaireId;

    private Nom nom;

    private final Espece espece;
    private final ClasseHeros classeHeros;
    private final Caracteristiques caracteristiques;


    private final Niveau niveau;
    private final PointVie pvHeros;
    private final PointMagie pmHeros;

    private final Inventaire inventaire = new Inventaire();
    private Arme armeEquipee;
    private Armure armureEquipee;

    public Heros(UUID proprietaireId, Nom nom, Espece espece,
                 ClasseHeros classeHeros, Caracteristiques caracteristiques,
                 Niveau niveau, PointVie pvHeros, PointMagie pmHeros, Arme armeEquipee) {
        this.id = UUID.randomUUID();
        this.proprietaireId = Objects.requireNonNull(proprietaireId, "Le propriétaire est obligatoire.");
        this.nom = Objects.requireNonNull(nom, "Le nom est obligatoire.");
        this.espece = Objects.requireNonNull(espece, "L'espèce est obligatoire.");
        this.classeHeros = Objects.requireNonNull(classeHeros, "La classe est obligatoire.");
        this.caracteristiques = Objects.requireNonNull(caracteristiques, "Les caractéristiques sont obligatoires.");
        this.pvHeros = Objects.requireNonNull(pvHeros, "Les PV sont obligatoires.");
        this.pmHeros = Objects.requireNonNull(pmHeros, "Les PM sont obligatoires.");

        this.niveau = Objects.requireNonNull(niveau, "Le niveau est obligatoire.");
        this.armeEquipee = Objects.requireNonNull(armeEquipee, "L'arme équipée est obligatoire.");

        //TODO: Check si null ou pas (progres qui load)
        this.armureEquipee = (armureEquipee == null) ? new Armure(TypeArmure.AUCUNE) : armureEquipee;
    }



    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public UUID getProprietaireId() { return proprietaireId; }

    public String getNom() {
        return nom.getValue();
    }



    public Espece getEspece() { return espece; }

    public ClasseHeros getClasseHeros() { return classeHeros; }

    public int getCaracteristiques(TypeCaracteristique type) {
        return caracteristiques.getValeur(type);
    }

    public int getModificateur(TypeCaracteristique type) {
        return caracteristiques.getModificateur(type);
    }



    public int getNiveauActuel() { return niveau.getNiveauActuel(); }

    public int getExperience() { return niveau.getXpTotal(); }



    public int getPvActuel() {
        return pvHeros.getPvActuel();
    }

    public int getPvMax() { return pvHeros.getPvMax(); }

    public int getPmActuel() {
        return pmHeros.getPmActuel();
    }

    public int getPmMax() { return pmHeros.getPmMax(); }

    public boolean estHorsCombat() { return pvHeros.estHorsCombat(); }

    public void subirDegat(int quantite) { pvHeros.subirDegat(quantite); }

    public void guerrir(int quantite) { pvHeros.guerrir(quantite); }

    /** @return true si la dépense de magie a pu être faite, false si le héros n'a pas assez de PM. */
    public boolean depenserMagie(int quantite) {
        return pmHeros.depenser(quantite);
    }



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
        if (!classeHeros.peutUtiliser(arme.getTypeArme())) {
            throw new EquipementInvalideException(
                    "La classe " + classeHeros.getNom() + " ne peut pas utiliser : " + arme.getNom());
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

        if (!classeHeros.peutPorter(armure.getType())) {
            throw new EquipementInvalideException("La classe " + classeHeros.getNom() + " ne peut pas porter : " + armure.getNom());
        }
        this.armureEquipee = armure;
    }

    public int getClasseArmure() {
        int modDex = getModificateur(TypeCaracteristique.DEXTERITE);
        return armureEquipee.calculerClasseArmure(modDex);
    }

    public int utiliserPotion(Potion potion) {
        if (potion == null) {
            System.out.println("La potion est obligatoire");
            return -1;
        }

        if (!inventaire.possedeObjet(potion)) {
            throw new ObjetNonPossedeException("Le héros ne possède pas cette potion.");
        }
        int montantSoigne = potion.soigner(pvHeros);
        inventaire.retirerItem(potion);
        return montantSoigne;
    }


    @Override
    public String toString() {
        return "Heros{" + getNom() + ", " + espece.getNom() + "/" + classeHeros.getNom()
                + ", niveau " + getNiveauActuel() + "}";

    }
}
