package domain.heros.espece;

import domain.heros.caracteristique.TypeCaracteristique;
import java.util.Map;

public interface Espece {
    String getNom();
    Map<TypeCaracteristique, Integer> getModificateursEspece();
}