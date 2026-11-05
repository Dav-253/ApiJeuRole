package apijeurole.heros.domain.espece;

import apijeurole.heros.domain.caracteristique.TypeCaracteristique;
import java.util.Map;

public interface Espece {
    String getNom();
    Map<TypeCaracteristique, Integer> getModificateursEspece();
}