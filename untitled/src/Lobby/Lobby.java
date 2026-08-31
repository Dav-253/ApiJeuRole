package Lobby;

import Hero.Hero;

import java.util.ArrayList;
import java.util.UUID;

public class Lobby {

    private UUID lobbyID;
    private String lobbyName;

    private ArrayList<Joeur> joeursActif;
    private int max_joeur;

    public Lobby() {
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

}
