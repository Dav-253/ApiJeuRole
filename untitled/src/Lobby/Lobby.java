package Lobby;

import Hero.Hero;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

public class Lobby {

    private UUID lobbyID;
    private String lobbyName;

    private EtatLobby etatLobby;

    private ArrayList<Joeur> joeursActif;
    private int max_joeur;

    public Lobby() {
        this.etatLobby = EtatLobby.LOBBY;
        this.max_joeur = 4;
        this.joeursActif = new ArrayList<>();
    }

    public void ajoutJoueur(Joeur nouvJoeur) {
        if (joeursActif.size() >= max_joeur) {
            System.out.println("Le nombre de joeur max dans le lobby est atteint");
            //throw new
            return;
        }

        // Verifier si le joueur est deja present

        joeursActif.add(nouvJoeur);
        System.out.println("Nouvaux joueur ajouté");
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
                System.out.println("Le joueur a été enlevé du lobby");
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

        for (Joeur joeur : joeursActif) {
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



}
