package domain.heros.autre;

import erreur.exception.NomPersonnageInvalideException;

import java.util.regex.Pattern;

public class Nom {

    private static final Pattern pattern = Pattern.compile("^[a-zA-ZÀ-ÿ]+(?: [a-zA-ZÀ-ÿ]+)*$");

    private String nom;

    public Nom(String nom) {
        if (nom == null) {
            throw new NomPersonnageInvalideException("Le nom ne peut pas être nul");
        }

        String nomPropre = nom.trim();

        if (nomPropre.length() < 2 || nomPropre.length() > 30) {
            throw new NomPersonnageInvalideException("Le nom doit être entre 2 et 30 caractères");
        }

        if (!pattern.matcher(nomPropre).matches()) {
            throw new NomPersonnageInvalideException("Le nom contient des caractères invalides ou un mauvais espacement");
        }

        this.nom = nomPropre;
    }

    public String getValue() {
        return nom;
    }

}
