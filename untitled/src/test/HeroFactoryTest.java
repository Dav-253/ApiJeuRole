package domain.heros;

import domain.heros.autre.PointMagie;
import domain.heros.caracteristique.TypeCaracteristique;
import domain.heros.classe.ClasseHeros;
import domain.heros.espece.Espece;
import erreur.exception.NomDejaUtiliseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static domain.heros.caracteristique.TypeCaracteristique.*;
import static org.junit.jupiter.api.Assertions.*;

class HerosFactoryTest {

    private HerosFactory factory;
    private final UUID proprietaire = UUID.randomUUID();

    // Espèces de test (Espece est une interface)
    private static Espece espece(String nom, Map<TypeCaracteristique, Integer> bonus) {
        return new Espece() {
            public String getNom() { return nom; }
            public Map<TypeCaracteristique, Integer> getModificateursEspece() { return bonus; }
        };
    }

    private static final Espece HUMAIN = espece("Humain", Map.of());
    private static final Espece ORC = espece("Orc", Map.of(INTELLIGENCE, -1));

    private static Map<TypeCaracteristique, Integer> saisies(int valeur) {
        Map<TypeCaracteristique, Integer> m = new EnumMap<>(TypeCaracteristique.class);
        for (TypeCaracteristique t : TypeCaracteristique.values()) m.put(t, valeur);
        return m;
    }

    private Heros creer(String nom, Espece espece, ClasseHeros classe, Map<TypeCaracteristique, Integer> s) {
        return factory.creer(proprietaire, nom, espece, classe, s);
    }

    @BeforeEach
    void setUp() {
        Set<String> existants = Set.of("Thorin");
        factory = new HerosFactory(n -> existants.contains(n.getValue()));
    }

    // --- Départ ---

    @Test
    void heros_commence_niveau1_0xp_pv_et_pm_au_maximum() {
        Heros h = creer("Aria", HUMAIN, ClasseHeros.MAGE, saisies(10));
        assertEquals(1, h.getNiveauActuel());
        assertEquals(0, h.getExperience());
        assertEquals(h.getPvMax(), h.getPvActuel());
        assertEquals(h.getPmMax(), h.getPmActuel());
    }

    // --- Espèce : bonus / pénalités / plafond ---

    @Test
    void orc_perd_1_en_intelligence() {
        Heros h = creer("Grok", ORC, ClasseHeros.GUERRIER, saisies(10));
        assertEquals(9, h.getCaracteristiques(INTELLIGENCE));
        assertEquals(10, h.getCaracteristiques(FORCE));
    }

    @Test
    void valeur_plafonnee_a_20_apres_bonus() {
        Espece elfe = espece("Elfe", Map.of(DEXTERITE, 5));
        Heros h = creer("Legolas", elfe, ClasseHeros.RODEUR, saisies(18));
        assertEquals(20, h.getCaracteristiques(DEXTERITE));
    }

    // --- Valeurs saisies ---

    @Test
    void valeur_2_refusee() {
        assertThrows(IllegalArgumentException.class,
                () -> creer("Aria", HUMAIN, ClasseHeros.MAGE, saisies(2)));
    }

    @Test
    void valeur_19_refusee() {
        assertThrows(IllegalArgumentException.class,
                () -> creer("Aria", HUMAIN, ClasseHeros.MAGE, saisies(19)));
    }

    @Test
    void valeurs_3_et_18_acceptees() {
        assertDoesNotThrow(() -> creer("Aria", HUMAIN, ClasseHeros.MAGE, saisies(3)));
        assertDoesNotThrow(() -> creer("Brun", HUMAIN, ClasseHeros.MAGE, saisies(18)));
    }

    @Test
    void caracteristique_manquante_refusee() {
        Map<TypeCaracteristique, Integer> s = saisies(10);
        s.remove(SAGESSE);
        assertThrows(IllegalArgumentException.class,
                () -> creer("Aria", HUMAIN, ClasseHeros.MAGE, s));
    }

    // --- PV ---

    @Test
    void guerrier_con16_donne_15_pv() {
        Map<TypeCaracteristique, Integer> s = saisies(10);
        s.put(CONSTITUTION, 16);
        Heros h = creer("Conan", HUMAIN, ClasseHeros.GUERRIER, s);
        assertEquals(15, h.getPvMax());
    }

    @Test
    void pv_minimum_1_avec_con_tres_faible() {
        Espece fragile = espece("Fragile", Map.of(CONSTITUTION, -5));
        Map<TypeCaracteristique, Integer> s = saisies(10);
        s.put(CONSTITUTION, 3);
        Heros h = creer("Pip", fragile, ClasseHeros.MAGE, s);
        assertEquals(1, h.getPvMax());
    }

    // --- PM ---

    @Test
    void pm_max_par_classe() {
        assertEquals(10, creer("Mage", HUMAIN, ClasseHeros.MAGE, saisies(10)).getPmMax());
        assertEquals(8, creer("Clerc", HUMAIN, ClasseHeros.CLERC, saisies(10)).getPmMax());
        assertEquals(0, creer("Guerrier", HUMAIN, ClasseHeros.GUERRIER, saisies(10)).getPmMax());
        assertEquals(0, creer("Rodeur", HUMAIN, ClasseHeros.RODEUR, saisies(10)).getPmMax());
    }

    @Test
    void point_magie_accepte_un_max_de_0() {
        assertDoesNotThrow(() -> new PointMagie(0, 0));
    }

    // --- Nom ---

    @Test
    void noms_valides() {
        for (String nom : new String[]{"Aria", "Eldrin", "Jean Pierre"}) {
            assertDoesNotThrow(() -> creer(nom, HUMAIN, ClasseHeros.MAGE, saisies(10)), nom);
        }
    }

    @Test
    void noms_invalides() {
        String[] invalides = {"Mage123", "Thorin!", "_Gandalf_", "Player#42",
                " Aria", "Aria ", "A", "", "A".repeat(31)};
        for (String nom : invalides) {
            assertThrows(IllegalArgumentException.class,
                    () -> creer(nom, HUMAIN, ClasseHeros.MAGE, saisies(10)), "Devrait être refusé : '" + nom + "'");
        }
    }

    @Test
    void nom_deja_utilise_refuse() {
        assertThrows(NomDejaUtiliseException.class,
                () -> creer("Thorin", HUMAIN, ClasseHeros.GUERRIER, saisies(10)));
    }
}
