package heros.domain.espece;

import heros.domain.caracteristique.TypeCaracteristique;
import java.util.Map;

public interface Espece {
    String getNom();
    Map<TypeCaracteristique, Integer> getModificateursEspece();
}