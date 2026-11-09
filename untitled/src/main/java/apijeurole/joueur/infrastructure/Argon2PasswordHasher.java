package apijeurole.joueur.infrastructure;

import apijeurole.joueur.application.PasswordHasher;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class Argon2PasswordHasher implements PasswordHasher {

    private static final int ITERATIONS = 3;
    private static final int MEMOIRE_KIO = 65536;
    private static final int PARALLELISME = 1;

    private final Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    @Override
    public String hacher(String motDePasse) {
        char[] caracteres = motDePasse.toCharArray();
        try {
            return argon2.hash(ITERATIONS, MEMOIRE_KIO, PARALLELISME, caracteres);
        } finally {
            argon2.wipeArray(caracteres);
        }
    }

    @Override
    public boolean verifier(String motDePasseHache, String motDePasse) {
        char[] caracteres = motDePasse.toCharArray();
        try {
            return argon2.verify(motDePasseHache, caracteres);
        } finally {
            argon2.wipeArray(caracteres);
        }
    }
}