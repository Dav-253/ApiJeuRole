package lobby;


import domain.joueur.JoeurRepository;
import lobby.application.LobbyService;
import lobby.domain.LobbyRepository;
import lobby.infrastructure.InMemoryLobbyRepository;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * DÉMARRAGE — installer du module "lobby".
 *
 * <p>TODO étape 4 : ajouter la ressource REST du lobby, ses DTO → Command et son ExceptionMapper
 * (LobbyIntrouvable → 404, autres LobbyException → 422), puis les enregistrer dans
 * {@link #install(ResourceConfig)}.
 */
public class LobbyInstaller {

    private final LobbyService lobbyService;

    public LobbyInstaller(JoeurRepository joueurRepository) {
        LobbyRepository lobbyRepository = new InMemoryLobbyRepository();
        this.lobbyService = new LobbyService(lobbyRepository, joueurRepository);
    }

    public void install(ResourceConfig resourceConfig) {
        // Rien à enregistrer dans Jersey pour l'instant.
    }

    public LobbyService getLobbyService() {
        return lobbyService;
    }

}
