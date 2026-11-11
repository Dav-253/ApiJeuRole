package apijeurole.joueur.application;

import apijeurole.commun.domain.ActionInterditeException;
import apijeurole.commun.domain.RequeteInvalideException;
import apijeurole.joueur.domain.Joueur;
import apijeurole.joueur.domain.JoueurRepository;
import apijeurole.joueur.domain.exception.IdentifiantsInvalidesException;
import apijeurole.joueur.domain.exception.JoueurIntrouvableException;

import java.util.UUID;

public class JoueurService {

    private final JoueurRepository joueurRepository;
    private final PasswordHasher passwordHasher;
    private final GestionJetons gestionJetons;

    public JoueurService(JoueurRepository joueurRepository, PasswordHasher passwordHasher,
                         GestionJetons gestionJetons) {
        this.joueurRepository = joueurRepository;
        this.passwordHasher = passwordHasher;
        this.gestionJetons = gestionJetons;
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

    public SessionOuverte connecter(String courriel, String motDePasse) {
        exiger(courriel, "Le courriel est obligatoire.");
        exiger(motDePasse, "Le mot de passe est obligatoire.");

        Joueur joueur = joueurRepository.findByCourriel(courriel)
                .filter(j -> passwordHasher.verifier(j.getMotDePasseHache(), motDePasse))
                .orElseThrow(IdentifiantsInvalidesException::new);

        return new SessionOuverte(joueur.getId(), gestionJetons.creerJeton(joueur.getId()));
    }

    public Joueur consulterProfil(UUID joueurAuthentifieId, UUID joueurDemandeId) {
        Joueur joueur = joueurRepository.findById(joueurDemandeId)
                .orElseThrow(() -> new JoueurIntrouvableException(joueurDemandeId));
        if (!joueur.getId().equals(joueurAuthentifieId)) {
            throw new ActionInterditeException("Vous ne pouvez consulter que votre propre profil.");
        }
        return joueur;
    }

    static void exiger(String valeur, String message) {
        if (valeur == null || valeur.isEmpty()) {
            throw new RequeteInvalideException(message);
        }
    }
}
