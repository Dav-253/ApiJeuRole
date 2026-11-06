package apijeurole.lobby.application;


import apijeurole.joueur.domain.JoueurRepository;
import apijeurole.heros.domain.Heros;
import apijeurole.lobby.domain.Lobby;
import apijeurole.lobby.domain.LobbyRepository;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * TEMPORAIRE — à découper à l'étape 4 en un use case par intention
 * ({@code CreerLobbyUseCase}, {@code RejoindreLobbyUseCase}, {@code QuitterLobbyUseCase}…),
 * chacun avec sa Command et son Output, comme dans l'exemple du prof.
 *
 * <p>Il reçoit encore un {@link Heros} du domaine : le use case recevra plutôt un identifiant de
 * héros et ira chercher le héros via un gateway du module héros.
 */
public class LobbyService {

    private final LobbyRepository lobbyRepository;
    private final JoueurRepository joueurRepository;

    public LobbyService(LobbyRepository lobbyRepository, JoueurRepository joueurRepository) {
        this.lobbyRepository = lobbyRepository;
        this.joueurRepository = joueurRepository;
    }


    public Lobby createLobby() {
        Lobby lobby = new Lobby(UUID.randomUUID());
        lobbyRepository.save(lobby);
        return lobby;
    }

    public Collection<Lobby> listAllLobbies() {
        return lobbyRepository.findAll();
    }

    public Lobby getLobby(UUID lobbyId) {
        return lobbyRepository.fetchById(lobbyId);
    }

    public void joinLobbyWorkflow(UUID lobbyId, UUID joueurId, Heros herosSelectionne) {
        Lobby lobby = lobbyRepository.fetchById(lobbyId);
        joueurRepository.findById(joueurId)
                .orElseThrow(() -> new NoSuchElementException("Joueur introuvable : " + joueurId)); // 404

        lobby.rejoindreLobby(joueurId, herosSelectionne);
        lobbyRepository.save(lobby);
    }

    public void leaveLobbyWorkflow(UUID lobbyId, UUID joueurId) {
        Lobby lobby = lobbyRepository.fetchById(lobbyId);
        joueurRepository.findById(joueurId)
                .orElseThrow(() -> new NoSuchElementException("Joueur introuvable : " + joueurId));

        lobby.quitterLobby(joueurId);
        lobbyRepository.save(lobby);
    }

    public void toggleReadyWorkflow(UUID lobbyId, UUID joueurId, boolean estPret) {
        Lobby lobby = lobbyRepository.fetchById(lobbyId);

        lobby.modifierStatusPret(joueurId, estPret);
        lobbyRepository.save(lobby);
    }

    public void startMatchWorkflow(UUID lobbyId) {
        Lobby lobby = lobbyRepository.fetchById(lobbyId);

        lobby.lancerLaPartie();
        lobbyRepository.save(lobby);

        // TODO étape 4 : passer apijeurole.lobby.getSelectionsHeros().values() à la nouvelle Partie.
    }

    public void endMatchWorkflow(UUID lobbyId) {
        Lobby lobby = lobbyRepository.fetchById(lobbyId);

        lobby.terminerLaPartie();
        lobbyRepository.save(lobby);
    }
}
