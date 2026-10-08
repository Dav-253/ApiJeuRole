// ComportementCiblage.java
package domain.creature;

import domain.hero.Hero;
import java.util.List;

public interface ComportementCiblage {
    Hero choisirCible(List<Hero> herosActifs);
}