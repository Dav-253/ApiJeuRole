package apijeurole.joueur.domain;

import apijeurole.commun.domain.RequeteInvalideException;
import apijeurole.joueur.domain.exception.MotDePasseTropCourtException;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;


public class Joueur {

    public static final int LONGUEUR_MIN_MOT_DE_PASSE = 8;

    private static final Pattern PSEUDONYME = Pattern.compile("^[\\p{L}\\p{N}_]{3,20}$");
    private static final Pattern COURRIEL = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    private final UUID id;
    private final String pseudonyme;
    private final String courriel;
    private final String motDePasseHache;

    public Joueur(UUID id, String pseudonyme, String courriel, String motDePasseHache) {
        this.id = Objects.requireNonNull(id, "L'identifiant du joueur est obligatoire.");
        this.pseudonyme = Objects.requireNonNull(pseudonyme, "Le pseudonyme est obligatoire.");
        this.courriel = Objects.requireNonNull(courriel, "Le courriel est obligatoire.");
        this.motDePasseHache = Objects.requireNonNull(motDePasseHache, "Le mot de passe haché est obligatoire.");
    }

    public static void verifierFormat(String pseudonyme, String courriel) {
        if (!PSEUDONYME.matcher(pseudonyme).matches()) {
            throw new RequeteInvalideException(
                    "Le pseudonyme doit contenir de 3 à 20 caractères : lettres, chiffres ou tiret bas.");
        }
        if (!COURRIEL.matcher(courriel).matches()) {
            throw new RequeteInvalideException("Le courriel n'est pas une adresse valide.");
        }
    }

    public static void verifierMotDePasse(String motDePasse) {
        if (motDePasse.length() < LONGUEUR_MIN_MOT_DE_PASSE) {
            throw new MotDePasseTropCourtException(LONGUEUR_MIN_MOT_DE_PASSE);
        }
    }

    public UUID getId() {
        return id;
    }

    public String getPseudonyme() {
        return pseudonyme;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getMotDePasseHache() {
        return motDePasseHache;
    }
}