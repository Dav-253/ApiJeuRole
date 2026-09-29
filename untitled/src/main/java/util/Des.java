package util;

import java.util.Random;

public class Des {

    private static final Random rand = new Random();

    private Des() {}

    public static int lancer(int nbDes, int nbFacesDe) {
        if (nbDes <= 0 || nbFacesDe <= 0) {
            throw new IllegalArgumentException("Le nombre de dés et le nombre de faces doivent être positifs.");
        }

        int total = 0;
        for (int i = 0; i < nbDes; i++) {
            total += rand.nextInt(nbFacesDe) + 1;
        }
        return total;
    }


}
