package apijeurole.lobby;
import apijeurole.heros.domain.HerosRepository;
import apijeurole.joueur.domain.JoueurRepository;
import apijeurole.lobby.api.LobbyResource;
import apijeurole.lobby.application.LobbyService;
import apijeurole.lobby.infrastructure.HerosGatewayAdapter;
import apijeurole.lobby.infrastructure.InMemoryLobbyRepository;
import apijeurole.partie.domain.PartieRepository;
import org.glassfish.jersey.server.ResourceConfig;
/** DÉMARRAGE — installer du module lobby. */
public class LobbyInstaller {
    private final LobbyService lobbyService;
    public LobbyInstaller(JoueurRepository joueurRepository, HerosRepository herosRepository,
                          PartieRepository partieRepository) {
        this.lobbyService = new LobbyService(
                new InMemoryLobbyRepository(), joueurRepository, new HerosGatewayAdapter(herosRepository),
                partieRepository);
    }
    public void install(ResourceConfig resourceConfig) {
        resourceConfig.register(new LobbyResource(lobbyService));
    }
}