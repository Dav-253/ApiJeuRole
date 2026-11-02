package util;

public class DesAleatoires implements LanceurDes {

    @Override
    public int lancer(int nbDes, int nbFaces) {
        return Des.lancer(nbDes, nbFaces);
    }
}