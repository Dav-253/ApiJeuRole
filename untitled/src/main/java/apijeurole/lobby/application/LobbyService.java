package apijeurole.lobby.application;


import apijeurole.joueur.domain.Joueur;
import apijeurole.joueur.domain.JoueurRepository;
import apijeurole.lobby.application.LobbyVue.HerosVue;
import apijeurole.lobby.application.LobbyVue.MembreVue;
import apijeurole.lobby.domain.EtatLobby;
import apijeurole.lobby.domain.HerosGateway;
import apijeurole.lobby.domain.Lobby;
import apijeurole.lobby.domain.LobbyRepository;
import apijeurole.lobby.domain.exception.DejaDansUnLobbyException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LobbyService {

    private final LobbyRepository lobbyRepository;
    private final JoueurRepository joueurRepository;
    private final HerosGateway herosGateway;

    public LobbyService(LobbyRepository lobbyRepository, JoueurRepository joueurRepository,
                        HerosGateway herosGateway) {
        this.lobbyRepository = lobbyRepository;
        this.joueurRepository = joueurRepository;
        this.herosGateway = herosGateway;
    }

    /** Le joueur devient l'hôte et le premier membre. Un joueur ne peut créer un lobby que s'il n'est dans aucun lobby ouvert. */
    public LobbyVue creerLobby(UUID joueurId) {
        if (lobbyOuvertDe(joueurId).isPresent()) {
            throw new DejaDansUnLobbyException();
        }
        Lobby lobby = Lobby.creer(UUID.randomUUID(), joueurId);
        lobbyRepository.save(lobby);
        return vue(lobby);
    }

    Optional<Lobby> lobbyOuvertDe(UUID joueurId) {
        return lobbyRepository.findAll().stream()
                .filter(l -> l.getEtat() == EtatLobby.OPEN && l.contient(joueurId))
                .findFirst();
    }

    LobbyVue vue(Lobby lobby) {
        List<MembreVue> membres = lobby.getJoueursIds().stream()
                .map(joueurId -> new MembreVue(joueurId, pseudonyme(joueurId), herosVue(lobby, joueurId)))
                .toList();
        return new LobbyVue(lobby.getId(), lobby.getEtat(), lobby.getHostId(), membres, lobby.placesDisponibles());
    }

    private String pseudonyme(UUID joueurId) {
        return joueurRepository.findById(joueurId).map(Joueur::getPseudonyme).orElse("?");
    }

    private HerosVue herosVue(Lobby lobby, UUID joueurId) {
        return lobby.herosDe(joueurId)
                .flatMap(herosGateway::trouver)
                .map(h -> new HerosVue(h.id(), h.nom()))
                .orElse(null);
    }
}
