package Hero.caracteristique;

public enum TypeCaracteristique {
    FORCE("Force"),
    DEXTERITE("Dextérité"),
    CONSTITUTION("Constitution"),
    INTELLIGENCE("Intelligence"),
    SAGESSE("Sagesse"),
    CHARISME("Charisme");

    private final String caracteristique;

    TypeCaracteristique(String caracteristique) {
        this.caracteristique = caracteristique;
    }

    public String getCaracteristique() { return caracteristique; }
}
