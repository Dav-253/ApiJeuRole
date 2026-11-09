package apijeurole.commun.domain;


public enum CategorieErreur {
    REQUETE_INVALIDE,   // 400
    NON_AUTHENTIFIE,    // 401
    INTERDIT,           // 403
    INTROUVABLE,        // 404
    CONFLIT,            // 409
    REGLE_REFUSEE;     // 422
}