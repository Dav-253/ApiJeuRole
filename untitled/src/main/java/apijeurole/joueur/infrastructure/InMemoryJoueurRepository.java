package apijeurole.joueur.infrastructure;

import apijeurole.joueur.domain.Joueur;
import apijeurole.joueur.domain.JoueurRepository;
import apijeurole.joueur.domain.exception.CourrielDejaUtiliseException;
import apijeurole.joueur.domain.exception.PseudonymeDejaUtiliseException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryJoueurRepository implements JoueurRepository {

    private final Map<UUID, Joueur> parId = new HashMap<>();
    private final Map<String, UUID> idParPseudonyme = new HashMap<>();
    private final Map<String, UUID> idParCourriel = new HashMap<>();

    @Override
    public synchronized void save(Joueur joueur) {
        UUID parPseudo = idParPseudonyme.get(cle(joueur.getPseudonyme()));
        if (parPseudo != null && !parPseudo.equals(joueur.getId())) {
            throw new PseudonymeDejaUtiliseException(joueur.getPseudonyme());
        }
        UUID parCourriel = idParCourriel.get(cle(joueur.getCourriel()));
        if (parCourriel != null && !parCourriel.equals(joueur.getId())) {
            throw new CourrielDejaUtiliseException();
        }
        parId.put(joueur.getId(), joueur);
        idParPseudonyme.put(cle(joueur.getPseudonyme()), joueur.getId());
        idParCourriel.put(cle(joueur.getCourriel()), joueur.getId());
    }

    @Override
    public synchronized Optional<Joueur> findById(UUID id) {
        return Optional.ofNullable(parId.get(id));
    }

    @Override
    public synchronized Optional<Joueur> findByCourriel(String courriel) {
        return Optional.ofNullable(idParCourriel.get(cle(courriel))).map(parId::get);
    }

    private static String cle(String valeur) {
        return valeur.toLowerCase(Locale.ROOT);
    }



}
