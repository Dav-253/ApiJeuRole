package apijeurole.commun.api;

import apijeurole.commun.domain.CategorieErreur;

public enum HttpErrorMapping {
    REQUETE_INVALIDE(400),
    NON_AUTHENTIFIE(401),
    INTERDIT(403),
    INTROUVABLE(404),
    CONFLIT(409),
    REGLE_REFUSEE(422);


    private final int status;

    HttpErrorMapping(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public static int statusPour(CategorieErreur categorie) {
        return HttpErrorMapping.valueOf(categorie.name()).getStatus();
    }
}
