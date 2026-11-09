package apijeurole.joueur.application;

import apijeurole.commun.domain.ErreurMetier;
import apijeurole.joueur.domain.Joueur;
import apijeurole.joueur.infrastructure.InMemoryJoueurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InscriptionTest {

    private InMemoryJoueurRepository repository;
    private JoueurService service;

    @BeforeEach
    void preparer() {
        repository = new InMemoryJoueurRepository();
        service = new JoueurService(repository, new FauxPasswordHasher());
    }

    @Test
    void une_inscription_valide_cree_le_joueur_et_conserve_seulement_le_hachage() {
        Joueur joueur = service.inscrire("Aria", "aria@mail.com", "dragon-2026");

        assertEquals("Aria", joueur.getPseudonyme());
        assertEquals("aria@mail.com", joueur.getCourriel());
        assertEquals(false, joueur.getMotDePasseHache().equals("dragon-2026"));
        assertEquals(joueur, repository.findById(joueur.getId()).get());
    }

    @Test
    void le_pseudonyme_est_unique_sans_tenir_compte_des_majuscules() {
        service.inscrire("Aria", "aria@mail.com", "dragon-2026");

        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.inscrire("aria", "autre@mail.com", "dragon-2026"));
        assertEquals("PSEUDONYM_TAKEN", erreur.getCode());
    }

    @Test
    void le_courriel_est_unique_sans_tenir_compte_des_majuscules() {
        service.inscrire("Aria", "aria@mail.com", "dragon-2026");

        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.inscrire("Bram", "ARIA@Mail.com", "dragon-2026"));
        assertEquals("EMAIL_TAKEN", erreur.getCode());
    }

    @Test
    void un_mot_de_passe_de_7_caracteres_est_refuse_et_8_est_accepte() {
        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.inscrire("Aria", "aria@mail.com", "1234567"));
        assertEquals("PASSWORD_TOO_SHORT", erreur.getCode());

        service.inscrire("Aria", "aria@mail.com", "12345678");
    }

    @Test
    void un_pseudonyme_trop_court_trop_long_ou_avec_symboles_est_refuse() {
        for (String mauvais : new String[]{"ab", "a".repeat(21), "Ar ia", "Ari-a", "Ari@"}) {
            ErreurMetier erreur = assertThrows(ErreurMetier.class,
                    () -> service.inscrire(mauvais, "aria@mail.com", "dragon-2026"));
            assertEquals("INVALID_REQUEST", erreur.getCode());
        }
        service.inscrire("a_1", "a@mail.com", "dragon-2026");
        service.inscrire("a".repeat(20), "b@mail.com", "dragon-2026");
    }

    @Test
    void un_courriel_mal_forme_est_refuse() {
        for (String mauvais : new String[]{"aria", "aria@", "@mail.com", "aria@mail", "a b@mail.com"}) {
            ErreurMetier erreur = assertThrows(ErreurMetier.class,
                    () -> service.inscrire("Aria", mauvais, "dragon-2026"));
            assertEquals("INVALID_REQUEST", erreur.getCode());
        }
    }

    @Test
    void un_champ_absent_est_refuse() {
        assertEquals("INVALID_REQUEST", assertThrows(ErreurMetier.class,
                () -> service.inscrire(null, "aria@mail.com", "dragon-2026")).getCode());
        assertEquals("INVALID_REQUEST", assertThrows(ErreurMetier.class,
                () -> service.inscrire("Aria", null, "dragon-2026")).getCode());
        assertEquals("INVALID_REQUEST", assertThrows(ErreurMetier.class,
                () -> service.inscrire("Aria", "aria@mail.com", null)).getCode());
    }
}