package apijeurole.joueur.application;

import apijeurole.commun.domain.ErreurMetier;
import apijeurole.joueur.infrastructure.InMemoryGestionJetons;
import apijeurole.joueur.infrastructure.InMemoryJoueurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConnexionTest {

    private JoueurService service;
    private InMemoryGestionJetons jetons;

    @BeforeEach
    void preparer() {
        jetons = new InMemoryGestionJetons();
        service = new JoueurService(new InMemoryJoueurRepository(), new FauxPasswordHasher(), jetons);
        service.inscrire("Aria", "aria@mail.com", "dragon-2026");
    }

    @Test
    void une_connexion_valide_donne_un_jeton_qui_identifie_le_joueur() {
        SessionOuverte session = service.connecter("aria@mail.com", "dragon-2026");

        assertEquals(session.joueurId(), jetons.trouverJoueur(session.jeton()).get());
    }

    @Test
    void le_courriel_est_reconnu_sans_tenir_compte_des_majuscules() {
        service.connecter("ARIA@Mail.COM", "dragon-2026");
    }

    @Test
    void courriel_inconnu_et_mot_de_passe_faux_donnent_exactement_la_meme_erreur() {
        ErreurMetier inconnu = assertThrows(ErreurMetier.class,
                () -> service.connecter("inconnu@mail.com", "dragon-2026"));
        ErreurMetier faux = assertThrows(ErreurMetier.class,
                () -> service.connecter("aria@mail.com", "mauvais-mdp"));

        assertEquals("INVALID_CREDENTIALS", inconnu.getCode());
        assertEquals(inconnu.getCode(), faux.getCode());
        assertEquals(inconnu.getMessage(), faux.getMessage());
    }

    @Test
    void un_champ_absent_est_refuse_avec_400() {
        assertEquals("INVALID_REQUEST",
                assertThrows(ErreurMetier.class, () -> service.connecter(null, "dragon-2026")).getCode());
        assertEquals("INVALID_REQUEST",
                assertThrows(ErreurMetier.class, () -> service.connecter("aria@mail.com", null)).getCode());
    }

    @Test
    void deux_connexions_donnent_deux_jetons_differents() {
        String premier = service.connecter("aria@mail.com", "dragon-2026").jeton();
        String second = service.connecter("aria@mail.com", "dragon-2026").jeton();

        assertEquals(false, premier.equals(second));
    }

    @Test
    void un_jeton_inconnu_ne_identifie_personne() {
        assertEquals(false, jetons.trouverJoueur("faux-jeton").isPresent());
        assertEquals(false, jetons.trouverJoueur(null).isPresent());
    }
}