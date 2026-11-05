package apijeurole.domain.creature;

import apijeurole.util.DesAleatoires;

public final class CreatureFactory {

    private CreatureFactory() {}

    public static Creature creerGobelin() {
        return new Creature("Gobelin", 7, 12, 2, 50,
                3, 1, 6, 1, new CiblePlusFaible());
    }

    public static Creature creerOrc() {
        return new Creature("Orc", 15, 13, 1, 100,
                4, 1, 8, 2, new CiblePlusFort());
    }

    public static Creature creerTroll() {
        return new Creature("Troll", 35, 15, 0, 300,
                6, 2, 6, 3, new CibleAleatoire(new DesAleatoires()));
    }
}