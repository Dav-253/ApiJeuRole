package apijeurole.heros.api;

import java.util.Map;

/** Corps JSON de la création d'un héros (champs publics pour la liaison JSON-B). */
public class CreerHerosRequest {
    public String nom;
    public String espece;
    public String classe;
    /** Clés : force, dexterite, constitution, intelligence, sagesse, charisme (insensible à la casse). */
    public Map<String, Integer> caracteristiques;
}
