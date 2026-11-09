package apijeurole.joueur.application;

public class FauxPasswordHasher implements PasswordHasher {

    @Override
    public String hacher(String motDePasse) {
        return "hache(" + motDePasse + ")";
    }

    @Override
    public boolean verifier(String motDePasseHache, String motDePasse) {
        return motDePasseHache.equals(hacher(motDePasse));
    }
}