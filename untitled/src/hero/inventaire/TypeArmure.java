package Hero.inventaire;

public enum TypeArmure {

    CUIR("Armure de cuir", 11, true),
    MAILLES("Armure de mailles", 16, false);

    private final String nom;
    private final int protectionBase;
    private final boolean bonusDexteriteApplicable;

    TypeArmure(String nom, int protectionBase, boolean bonusDexteriteApplicable) {
        this.nom = nom;
        this.protectionBase = protectionBase;
        this.bonusDexteriteApplicable = bonusDexteriteApplicable;
    }

    public String getNom() {
        return nom;
    }

    public int getProtectionBase() {
        return protectionBase;
    }

    public boolean isBonusDexteriteApplicable() {
        return bonusDexteriteApplicable;
    }


    public static int calculerClasseArmure(TypeArmure armure, int modificateurDexterite) {
        if (armure == null) {
            return 10 + modificateurDexterite;
        }
        return armure.bonusDexteriteApplicable
                ? armure.protectionBase + modificateurDexterite
                : armure.protectionBase;
    }


}
