package apijeurole.domain.lobby;

import apijeurole.domain.joueur.Joeur;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Lobby {

    private UUID lobbyID;

    private final Map<UUID, Joeur> joeursActif = new HashMap<>();
    private final Map<UUID, Boolean> statusPret = new ConcurrentHashMap<>();


    private EtatLobby etatLobby;

    private final int max_joeurs;

    public Lobby(UUID id) {
        this.lobbyID = id;

        this.etatLobby = EtatLobby.LOBBY;
        this.max_joeurs = 4;
    }

    public UUID getId() {
        return lobbyID;
    }

    public boolean lobbyEstPlein() {
        return joeursActif.size() >= max_joeurs;
    }

    public EtatLobby getEtatLobby() {
        return etatLobby;
    }


    public void rejoindreLobby(Joeur joeur) {
        if (joeur.getId() == null || joeur.getHerosSelectionner() == null) {
            throw new IllegalArgumentException("Informations du apijeurole.joueur ou du héros invalides");
        }

        verifierLobbyEstOuvert();

        if (lobbyEstPlein()) {
            throw new IllegalStateException("Le apijeurole.lobby est plein. Maximum de " + max_joeurs + " joueurs.");
        }

        if (joueurDejaPresent(joeur.getId())) {
            throw new IllegalStateException("Ce main.apijeurole.domain.apijeurole.joueur est déjà présent dans le main.apijeurole.domain.apijeurole.lobby");
        }

        joeursActif.put(joeur.getId(), joeur);
        System.out.println("Nouvaux main.apijeurole.domain.apijeurole.joueur ajouté");
    }

    public void quitterLobby(Joeur joeur) {
        if (joeur.getId() == null) return;
        verifierLobbyEstOuvert();

        joeursActif.remove(joeur);
        statusPret.remove(joeur.getId());
    }

    public void modifierStatusPret(UUID joueurId, boolean estPret) {
        if (!joeursActif.containsKey(joueurId)) {
            throw new IllegalStateException("Le apijeurole.joueur n'est pas présent dans ce apijeurole.lobby.");
        }

        verifierLobbyEstOuvert();
        statusPret.put(joueurId, estPret);
    }

    public void lancerLaPartie() {
        verifierLobbyEstOuvert();

        if (joeursActif.isEmpty()) {
            throw new IllegalStateException("Impossible de lancer une apijeurole.partie sans héros.");
        }

        for (Map.Entry<UUID, Boolean> entry : statusPret.entrySet()) {
            if (!entry.getValue()) {
                throw new IllegalStateException("Impossible de commencer: tous les joueurs ne sont pas prêts.");
            }
        }


        this.etatLobby = EtatLobby.ACTIVE;
    }

    private void verifierLobbyEstOuvert() {
        if (this.etatLobby != EtatLobby.LOBBY) {
            throw new IllegalStateException("Le apijeurole.lobby n'est plus ouvert aux modifications (Partie lancée ou en cours).");
        }


    }

    private boolean joueurDejaPresent(UUID idJoueur) {
        return joeursActif.containsKey(idJoueur);
    }


}
