package apijeurole.lobby.domain;

import apijeurole.heros.domain.Heros;
import apijeurole.lobby.domain.exception.*;

import java.util.*;


/**
 * Un lobby accueille au plus 4 joueurs. Son créateur en est l'hôte. Les membres sont gardés dans
 * leur ordre d'arrivée ; chacun a, ou non, un héros sélectionné (on ne garde que l'identifiant).
 */
public class Lobby {

    public static final int MAX_JOUEURS = 4;

    private final UUID id;
    private UUID hostId;
    private EtatLobby etat = EtatLobby.OPEN;
    private final Map<UUID, UUID> herosParJoueur = new LinkedHashMap<>();


    public Lobby(UUID id, UUID hostId) {
        this.id = Objects.requireNonNull(id, "L'identifiant du lobby est obligatoire.");
        this.hostId = Objects.requireNonNull(hostId, "L'hôte est obligatoire.");
    }

    public static Lobby creer(UUID id, UUID hostId) {
        Lobby lobby = new Lobby(id, hostId);
        lobby.herosParJoueur.put(hostId, null);
        return lobby;
    }

    public UUID getId() {
        return id;
    }

    public synchronized UUID getHostId() {
        return hostId;
    }

    public synchronized EtatLobby getEtat() {
        return etat;
    }

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


}
