package apijeurole.joueur;

import apijeurole.joueur.api.JoueurResource;
import apijeurole.joueur.application.JoueurService;
import apijeurole.joueur.application.PasswordHasher;
import apijeurole.joueur.domain.JoueurRepository;
import org.glassfish.jersey.server.ResourceConfig;

public class JoueurInstaller {

    private final JoueurService joueurService;

    public JoueurInstaller(JoueurRepository joueurRepository, PasswordHasher passwordHasher) {
        this.joueurService = new JoueurService(joueurRepository, passwordHasher);
    }

    public void install(ResourceConfig resourceConfig) {
        resourceConfig.register(new JoueurResource(joueurService));
    }
}
