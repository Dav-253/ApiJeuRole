package apijeurole.joueur.application;

import apijeurole.commun.domain.RequeteInvalideException;
import apijeurole.joueur.domain.Joueur;
import apijeurole.joueur.domain.JoueurRepository;

import java.util.UUID;

public class JoueurService {

    private final JoueurRepository joueurRepository;
    private final PasswordHasher passwordHasher;

    public JoueurService(JoueurRepository joueurRepository, PasswordHasher passwordHasher) {
        this.joueurRepository = joueurRepository;
        this.passwordHasher = passwordHasher;
    }

    public Joueur inscrire(String pseudonyme, String courriel, String motDePasse) {
        exiger(pseudonyme, "Le pseudonyme est obligatoire.");
        exiger(courriel, "Le courriel est obligatoire.");
        exiger(motDePasse, "Le mot de passe est obligatoire.");

        Joueur.verifierFormat(pseudonyme, courriel);
        Joueur.verifierMotDePasse(motDePasse);

        Joueur joueur = new Joueur(UUID.randomUUID(), pseudonyme, courriel, passwordHasher.hacher(motDePasse));
        joueurRepository.save(joueur);
        return joueur;
    }

    static void exiger(String valeur, String message) {
        if (valeur == null || valeur.isEmpty()) {
            throw new RequeteInvalideException(message);
        }
    }
}
