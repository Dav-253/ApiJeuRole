package apijeurole;

import apijeurole.joueur.infrastructure.persistence.InMemoryJoueurRepository;
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

        // Installers des modules
        new LobbyInstaller(new InMemoryJoueurRepository()).install(config);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:" + port + "/"), config);
    }

    private static int lirePort() {
        String port = System.getenv("PORT");
        return port != null ? Integer.parseInt(port) : PORT_PAR_DEFAUT;
    }
}
