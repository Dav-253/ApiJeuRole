package apijeurole.commun.domain;

import java.util.Objects;

public abstract class ErreurMetier extends RuntimeException {

    private final String code;
    private final CategorieErreur categorie;

    protected ErreurMetier(String code, CategorieErreur categorie, String description) {
        super(description);
        this.code = Objects.requireNonNull(code);
        this.categorie = Objects.requireNonNull(categorie);
    }

    public String getCode() {
        return code;
    }

    public CategorieErreur getCategorie() {
        return categorie;
    }
}
