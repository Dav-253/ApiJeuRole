package apijeurole.joueur.application;

public interface PasswordHasher {
    String hacher(String motDePasse);
    boolean verifier(String motDePasseHache, String motDePasse);
}