package apijeurole.lobby.domain;

import apijeurole.heros.domain.Heros;
import apijeurole.lobby.domain.exception.*;

import java.util.*;

public class Lobby {

    private static final int MAX_JOUEURS = 4;

    private final UUID id;
    private final Map<UUID, Heros> selectionsHeros = new LinkedHashMap<>();
    private final Set<UUID> joueursPrets = new HashSet<>();
    private EtatLobby etatLobby = EtatLobby.LOBBY;


    public Lobby(UUID id) {
        this.id = Objects.requireNonNull(id, "L'identifiant du apijeurole.lobby est obligatoire.");
    }

    public UUID getId() {
        return id;
    }

    public synchronized EtatLobby getEtatLobby() {
        return etatLobby;
    }

    public synchronized boolean lobbyEstPlein() {
        return selectionsHeros.size() >= MAX_JOUEURS;
    }

    public synchronized int getNombreJoeurs() {
        return selectionsHeros.size();
    }

    public synchronized List<UUID> getJoueursIds() {
        return new ArrayList<>(selectionsHeros.keySet());
    }

    public synchronized boolean jouerEstPret(UUID joueurId) {
        return joueursPrets.contains(joueurId);
    }

    public synchronized Map<UUID, Heros> getSelectionsHeros() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(selectionsHeros));
    }

    public synchronized void rejoindreLobby(UUID joueurId, Heros heros) {
        Objects.requireNonNull(joueurId, "Le apijeurole.joueur est obligatoire.");
        Objects.requireNonNull(heros, "Le héros est obligatoire.");

        verifierLobbyEstOuvert();

        if (selectionsHeros.containsKey(joueurId)) {
            throw new JoueurDejaPresentException();
        }
        if (lobbyEstPlein()) {
            throw new LobbyPleinException();
        }
        if (!heros.getProprietaireId().equals(joueurId)) {
            throw new HerosNonAutoriseException();
        }

        selectionsHeros.put(joueurId, heros);
    }

    public synchronized void quitterLobby(UUID joueurId) {
        verifierLobbyEstOuvert();
        verifierJoueurPresent(joueurId);

        selectionsHeros.remove(joueurId);
        joueursPrets.remove(joueurId);
    }

    public synchronized void modifierStatusPret(UUID joueurId, boolean estPret) {
        verifierLobbyEstOuvert();
        verifierJoueurPresent(joueurId);

        if (estPret) {
            joueursPrets.add(joueurId);
        } else  {
            joueursPrets.remove(joueurId);
        }
    }

    public synchronized void lancerLaPartie() {
        verifierLobbyEstOuvert();

        if (selectionsHeros.isEmpty()) {
            throw new LobbyVideException();
        }
        if (!joueursPrets.containsAll(selectionsHeros.keySet())) {
            throw new JoueursPasPretsException();
        }

        this.etatLobby = EtatLobby.ACTIVE;
    }

    public synchronized void terminerLaPartie() {
        if (etatLobby != EtatLobby.ACTIVE) {
            throw new LobbyFermeException();
        }
        this.etatLobby = EtatLobby.ENDED;
    }

    private void verifierLobbyEstOuvert() {
        if (etatLobby != EtatLobby.LOBBY) {
            throw new LobbyFermeException();
        }
    }

    private void verifierJoueurPresent(UUID joueurId) {
        if (!selectionsHeros.containsKey(joueurId)) {
            throw new JoueurAbsentException();
        }
    }
}
