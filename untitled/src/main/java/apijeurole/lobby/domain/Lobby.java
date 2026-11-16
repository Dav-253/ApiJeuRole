package apijeurole.lobby.domain;
import apijeurole.lobby.domain.exception.DejaDansUnLobbyException;
import apijeurole.lobby.domain.exception.JoueurAbsentException;
import apijeurole.lobby.domain.exception.JoueurDejaPresentException;
import apijeurole.lobby.domain.exception.LobbyIntrouvableException;
import apijeurole.lobby.domain.exception.LobbyPleinException;
import apijeurole.lobby.domain.exception.PartieDejaLanceeException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
/**
 * Un lobby accueille au plus 4 joueurs. Son créateur en est l'hôte. Les membres sont gardés dans
 * leur ordre d'arrivée ; chacun a, ou non, un héros sélectionné (on ne garde que l'identifiant).
 */
public class Lobby {
    public static final int MAX_JOUEURS = 4;
    private final UUID id;
    private UUID hostId;
    private EtatLobby etat = EtatLobby.OPEN;
    /** joueurId -> herosId (null tant que le joueur n'a pas choisi), dans l'ordre d'arrivée. */
    private final Map<UUID, UUID> herosParJoueur = new LinkedHashMap<>();
    private Lobby(UUID id, UUID hostId) {
        this.id = Objects.requireNonNull(id, "L'identifiant du lobby est obligatoire.");
        this.hostId = Objects.requireNonNull(hostId, "L'hôte est obligatoire.");
    }
    /** Le créateur devient l'hôte et le premier membre. */
    public static Lobby creer(UUID id, UUID hostId) {
        Lobby lobby = new Lobby(id, hostId);
        lobby.herosParJoueur.put(hostId, null);
        return lobby;
    }
    public synchronized UUID getId() {
        return id;
    }
    public synchronized UUID getHostId() {
        return hostId;
    }
    public synchronized EtatLobby getEtat() {
        return etat;
    }
    /** Identifiants des membres, dans l'ordre d'arrivée. */
    public synchronized List<UUID> getJoueursIds() {
        return new ArrayList<>(herosParJoueur.keySet());
    }
    public synchronized boolean contient(UUID joueurId) {
        return herosParJoueur.containsKey(joueurId);
    }
    public synchronized Optional<UUID> herosDe(UUID joueurId) {
        return Optional.ofNullable(herosParJoueur.get(joueurId));
    }
    public synchronized int nombreJoueurs() {
        return herosParJoueur.size();
    }
    public synchronized int placesDisponibles() {
        return MAX_JOUEURS - herosParJoueur.size();
    }
    /** Un lobby qui apparaît dans la liste : ouvert et avec de la place. */
    public synchronized boolean estOuvertAvecPlace() {
        return etat == EtatLobby.OPEN && herosParJoueur.size() < MAX_JOUEURS;
    }
    /**
     * Ajoute un joueur. Ordre des vérifications : partie déjà lancée, déjà membre, lobby plein,
     * déjà membre d'un autre lobby ouvert (information fournie par l'appelant).
     */
    public synchronized void rejoindre(UUID joueurId, boolean dejaDansUnAutreLobbyOuvert) {
        Objects.requireNonNull(joueurId, "Le joueur est obligatoire.");
        verifierOuvert();
        if (herosParJoueur.containsKey(joueurId)) {
            throw new JoueurDejaPresentException();
        }
        if (herosParJoueur.size() >= MAX_JOUEURS) {
            throw new LobbyPleinException();
        }
        if (dejaDansUnAutreLobbyOuvert) {
            throw new DejaDansUnLobbyException();
        }
        herosParJoueur.put(joueurId, null);
    }
    /**
     * Retire un joueur (et son héros sélectionné). Si l'hôte part, le joueur arrivé le plus tôt devient
     * l'hôte. Quand le dernier joueur part, le lobby est fermé.
     *
     * @throws PartieDejaLanceeException après le lancement de la partie
     * @throws JoueurAbsentException     si le joueur n'est pas membre
     */
    public synchronized void quitter(UUID joueurId) {
        verifierOuvert();
        if (!herosParJoueur.containsKey(joueurId)) {
            throw new JoueurAbsentException();
        }
        herosParJoueur.remove(joueurId);
        if (herosParJoueur.isEmpty()) {
            etat = EtatLobby.CLOSED;
        } else if (joueurId.equals(hostId)) {
            hostId = herosParJoueur.keySet().iterator().next();
        }
    }
    /** @throws PartieDejaLanceeException si la partie est lancée ; un lobby fermé est introuvable */
    private void verifierOuvert() {
        if (etat == EtatLobby.STARTED) {
            throw new PartieDejaLanceeException();
        }
        if (etat == EtatLobby.CLOSED) {
            throw new LobbyIntrouvableException(id);
        }
    }
}
