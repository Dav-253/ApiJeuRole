package domain.heros.inventaire;

public enum TypeArmure {

    AUCUNE("Aucune Armure", 10, true),
    CUIR("Armure de cuir", 11, true),
    MAILLES("Armure de mailles", 16, false);

    private final String nom;
    private final int protectionDeBase;
    private final boolean bonusDexteriteApplicable;

    TypeArmure(String nom, int protectionDeBase, boolean bonusDexteriteApplicable) {
        this.nom = nom;
        this.protectionDeBase = protectionDeBase;
        this.bonusDexteriteApplicable = bonusDexteriteApplicable;
    }

    public String getNom() {
        return nom;
    }

    public boolean getBonusDexteriteApplicable() {
        return bonusDexteriteApplicable;
    }

    public int getProtectionDeBase() {
        return protectionDeBase;
    }

    public int calculerClasseArmure(int modificateurDexterite) {
        return bonusDexteriteApplicable
                ? protectionDeBase + modificateurDexterite
                : protectionDeBase;
    }

}
