package main.domain.lobby;

import main.domain.joueur.Joeur;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Lobby {

    private UUID lobbyID;

    private final Map<UUID, Joeur> joeursActif = new HashMap<>();
    private final int max_joeurs;

    private EtatLobby etatLobby;

    public Lobby(UUID id) {
        this.lobbyID = id;

        this.etatLobby = EtatLobby.LOBBY;
        this.max_joeurs = 4;
    }

    public UUID getLobbyID() {
        return lobbyID;
    }

    public boolean lobbyEstPlein() {
        return joeursActif.size() >= max_joeurs;
    }

    public EtatLobby getEtatLobby() {
        return etatLobby;
    }

    public void ajoutJoueur(Joeur nouvJoeur) {
        if (nouvJoeur == null) {
            throw new IllegalStateException("Joueur invalide");
        }

        LobbyEstOuvert();

        if (lobbyEstPlein()) {
            throw new IllegalStateException("Le main.domain.lobby est plein. Joueurs maximum: " + max_joeurs);
        }

        // Verifier si le main.domain.joueur est deja present
        if (joueurDejaPresent(nouvJoeur.getId())) {
            throw new IllegalStateException("Ce main.domain.joueur est déjà présent dans le main.domain.lobby");
        }

        joeursActif.put(nouvJoeur.getId(), nouvJoeur);
        System.out.println("Nouvaux main.domain.joueur ajouté");
    }

    public void enleveJoueur(UUID idJoueurEnleve) {
        if (joeursActif.isEmpty()) {
            System.out.println("Il n'y a pas de joeurs qui peut etre enlever");
            //throw new
            return;
        }

        for (Joeur joeur : joeursActif) {
            if (joeur.getId().equals(idJoueurEnleve)) {
                joeursActif.remove(joeur);
                System.out.println("Le main.domain.joueur a été enlevé du main.domain.lobby");
                return;
            } else {
                System.out.println("Aucun joeur correspondant");
                //throw new
                return;
            }
        }
    }

    public void startJeux() {
        if (this.etatLobby == EtatLobby.ACTIVE) {
            System.out.println("Une partie est deja active");
            return;
        }

        for (Joeur joeur : joeursActif.values()) {
            if (!joeur.isReady()) {
                System.out.println("Impossible de commencer la partie (Des joeurs ne sont pas pret)");
                return;
            }
        }

        if (this.etatLobby == EtatLobby.LOBBY || this.etatLobby == EtatLobby.STARTING) {
            this.etatLobby = EtatLobby.ACTIVE;
            // Logice lorsque la partie commence
        }

    }

    // Fonction pour finir la partie


    private void LobbyEstOuvert() {
        if (etatLobby != EtatLobby.LOBBY) {
            throw new IllegalStateException(
                    "Lobby is no longer accepting changes."
            );
        }
    }

    private boolean joueurDejaPresent(UUID idJoueur) {
        return joeursActif.containsKey(idJoueur);
    }


}
