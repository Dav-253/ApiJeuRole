package apijeurole.joueur.application;

import apijeurole.commun.domain.ErreurMetier;
import apijeurole.joueur.domain.Joueur;
import apijeurole.joueur.infrastructure.InMemoryGestionJetons;
import apijeurole.joueur.infrastructure.InMemoryJoueurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProfilTest {

    private JoueurService service;
    private Joueur aria;
    private Joueur bram;

    @BeforeEach
    void preparer() {
        service = new JoueurService(new InMemoryJoueurRepository(), new FauxPasswordHasher(),
                new InMemoryGestionJetons());
        aria = service.inscrire("Aria", "aria@mail.com", "dragon-2026");
        bram = service.inscrire("Bram", "bram@mail.com", "dragon-2026");
    }

    @Test
    void un_joueur_consulte_son_propre_profil() {
        Joueur profil = service.consulterProfil(aria.getId(), aria.getId());

        assertEquals("Aria", profil.getPseudonyme());
        assertEquals("aria@mail.com", profil.getCourriel());
    }

    @Test
    void le_profil_d_un_autre_joueur_est_interdit() {
        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.consulterProfil(aria.getId(), bram.getId()));

        assertEquals("FORBIDDEN", erreur.getCode());
    }

    @Test
    void un_joueur_inexistant_donne_404() {
        ErreurMetier erreur = assertThrows(ErreurMetier.class,
                () -> service.consulterProfil(aria.getId(), UUID.randomUUID()));

        assertEquals("PLAYER_NOT_FOUND", erreur.getCode());
    }
}