package main.domain.hero.espece;

import main.domain.hero.caracteristique.TypeCaracteristique;
import java.util.Map;

public interface Espece {
    String getNom();
    Map<TypeCaracteristique, Integer> getModificateursEspece();
}