package Hero.espece;

import Hero.caracteristique.TypeCaracteristique;
import java.util.Map;

public interface Espece {
    String getNom();
    Map<TypeCaracteristique, Integer> getModificateursEspece();
}