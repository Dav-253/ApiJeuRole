package apijeurole;

import apijeurole.commun.api.ErreurMetierMapper;
import apijeurole.heros.domain.HerosRepository;
import apijeurole.heros.infrastructure.InMemoryHerosRepository;
import apijeurole.joueur.JoueurInstaller;
import apijeurole.joueur.domain.JoueurRepository;
import apijeurole.joueur.infrastructure.Argon2PasswordHasher;
import apijeurole.joueur.infrastructure.InMemoryGestionJetons;
import apijeurole.joueur.infrastructure.InMemoryJoueurRepository;
import apijeurole.lobby.LobbyInstaller;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {


    private static final int PORT_PAR_DEFAUT = 8080;

    public static void main(String[] args) {
        int port = lirePort();
        HttpServer server = demarrer(port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Arrêt du serveur...");
            server.shutdownNow();
        }));

        System.out.println("Serveur démarré sur http://localhost:" + port);
        System.out.println("Ctrl+C pour arrêter.");
    }

    public static HttpServer demarrer(int port) {
        ResourceConfig config = new ResourceConfig();

        JoueurRepository joueurRepository = new InMemoryJoueurRepository();
        HerosRepository herosRepository = new InMemoryHerosRepository();

        config.register(new ErreurMetierMapper());

        // Installers des modules
        new JoueurInstaller(joueurRepository, new Argon2PasswordHasher(), new InMemoryGestionJetons()).install(config);
        new LobbyInstaller(joueurRepository, herosRepository).install(config);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:" + port + "/"), config);
    }

    private static int lirePort() {
        String port = System.getenv("PORT");
        return port != null ? Integer.parseInt(port) : PORT_PAR_DEFAUT;
    }
}