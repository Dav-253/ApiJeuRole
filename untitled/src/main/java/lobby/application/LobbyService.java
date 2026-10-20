package lobby.application;

import domain.heros.Heros;
import domain.joueur.Joeur;
import domain.joueur.JoeurRepository;
import domain.lobby.Lobby;
import domain.lobby.LobbyRepository;

import java.util.Collection;
import java.util.UUID;


public class LobbyService {

    // Dedicated repository layer to mimic database operations.
    private final LobbyRepository lobbyRepository;
    private final JoeurRepository joeurRepository;

    public LobbyService(LobbyRepository lobbyRepository, JoeurRepository joeurRepository) {
        this.lobbyRepository = lobbyRepository;
        this.joeurRepository = joeurRepository;
    }

    public Lobby createLobby() {
        UUID newId = UUID.randomUUID();

        Lobby lobby = new Lobby(newId);

        lobbyRepository.save(lobby);
        return lobby;
    }

    public Collection<Lobby> listAllLobbies() {
        return lobbyRepository.findAll();
    }

    public Lobby getLobby(UUID lobbyId) {
        return lobbyRepository.findById(lobbyId)
                .orElseThrow(() -> new IllegalArgumentException("Lobby introuvable avec l'ID: " + lobbyId));
    }

    public void joinLobbyWorkflow(UUID lobbyId, UUID joueurId, Heros herosSelectionne) {
        Lobby lobby = getLobby(lobbyId);

        Joeur joueur = joeurRepository.findById(joueurId)
                .orElseThrow(() -> new IllegalArgumentException("Joueur introuvable avec l'ID: " + joueurId));

        joueur.setHerosSelectionner(herosSelectionne);

        lobby.rejoindreLobby(joueur);
        lobbyRepository.save(lobby);
    }

    public void leaveLobbyWorkflow(UUID lobbyId, UUID joueurId) {
        Lobby lobby = getLobby(lobbyId);

        Joeur joeur = joeurRepository.findById(joueurId)
                .orElseThrow(() -> new IllegalArgumentException("Joueur introuvable avec l'ID: " + joueurId));

        lobby.quitterLobby(joeur);
        lobbyRepository.save(lobby);
    }


    public void toggleReadyWorkflow(UUID lobbyId, UUID joueurId, boolean estPret) {
        Lobby lobby = getLobby(lobbyId);

        lobby.modifierStatusPret(joueurId, estPret);

        lobbyRepository.save(lobby);
    }

    public void startMatchWorkflow(UUID lobbyId) {
        Lobby lobby = getLobby(lobbyId);

        // Valide si tout le monde est prêt
        lobby.lancerLaPartie();
        lobbyRepository.save(lobby);

        // NEXT STEP: Hand off lobby.getSelectionsHeros().values() over to the new Partie domain engine!
    }
}
