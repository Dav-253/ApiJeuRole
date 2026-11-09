package apijeurole.joueur;

import apijeurole.joueur.api.AuthentificationFilter;
import apijeurole.joueur.api.JoueurResource;
import apijeurole.joueur.api.SessionResource;
import apijeurole.joueur.application.GestionJetons;
import apijeurole.joueur.application.JoueurService;
import apijeurole.joueur.application.PasswordHasher;
import apijeurole.joueur.domain.JoueurRepository;
import org.glassfish.jersey.server.ResourceConfig;


public class JoueurInstaller {

    private final JoueurService joueurService;
    private final GestionJetons gestionJetons;

    public JoueurInstaller(JoueurRepository joueurRepository, PasswordHasher passwordHasher,
                           GestionJetons gestionJetons) {
        this.gestionJetons = gestionJetons;
        this.joueurService = new JoueurService(joueurRepository, passwordHasher, gestionJetons);
    }

    public void install(ResourceConfig resourceConfig) {
        resourceConfig.register(new AuthentificationFilter(gestionJetons));
        resourceConfig.register(new JoueurResource(joueurService));
        resourceConfig.register(new SessionResource(joueurService));
    }
}
