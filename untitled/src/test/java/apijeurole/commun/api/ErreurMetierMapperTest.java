package apijeurole.commun.api;

import apijeurole.commun.domain.CategorieErreur;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ErreurMetierMapperTest {

    @Test
    void chaque_categorie_du_domaine_doit_avoir_une_correspondance_http_valide() {
        for (CategorieErreur categorie : CategorieErreur.values()) {

            // Garantit que valueOf() ne lève pas d'IllegalArgumentException
            assertDoesNotThrow(() -> {
                HttpErrorMapping mapping = HttpErrorMapping.valueOf(categorie.name());

                // Garantit que le code HTTP associé est valide (ex: entre 400 et 599)
                assertThat(mapping.getStatus()).isAtLeast(400);
                assertThat(mapping.getStatus()).isLessThan(600);
            }, "La catégorie " + categorie.name() + " n'a pas de correspondance dans HttpErrorMapping !");
        }
    }

    @Test
    void verifie_les_codes_http_specifiques() {
        // Garde des assertions cibles pour valider la logique métier exacte
        assertThat(HttpErrorMapping.statusPour(CategorieErreur.REQUETE_INVALIDE)).isEqualTo(400);
        assertThat(HttpErrorMapping.statusPour(CategorieErreur.NON_AUTHENTIFIE)).isEqualTo(401);
        assertThat(HttpErrorMapping.statusPour(CategorieErreur.INTERDIT)).isEqualTo(403);
        assertThat(HttpErrorMapping.statusPour(CategorieErreur.INTROUVABLE)).isEqualTo(404);
        assertThat(HttpErrorMapping.statusPour(CategorieErreur.CONFLIT)).isEqualTo(409);
        assertThat(HttpErrorMapping.statusPour(CategorieErreur.REGLE_REFUSEE)).isEqualTo(422);
    }
}