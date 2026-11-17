package apijeurole.partie;
import apijeurole.partie.api.PartieExceptionMapper;
import apijeurole.partie.api.PartieNotFoundExceptionMapper;
import apijeurole.partie.api.PartieResponseMapper;
import apijeurole.partie.api.PartieRessource;
import apijeurole.partie.application.ConsulterPartieUseCase;
import apijeurole.partie.application.PartieOutputMapper;
import apijeurole.partie.domain.PartieRepository;
import org.glassfish.jersey.server.ResourceConfig;
/** DÉMARRAGE — installer du module partie : GET /games/{id} et ses mappers d'erreurs. */
public class PartieInstaller {
    private final PartieRessource ressource;
    public PartieInstaller(PartieRepository partieRepository) {
        this.ressource = new PartieRessource(
                new ConsulterPartieUseCase(partieRepository, new PartieOutputMapper()),
                new PartieResponseMapper());
    }
    public void install(ResourceConfig resourceConfig) {
        resourceConfig.register(ressource);
        resourceConfig.register(new PartieExceptionMapper());
        resourceConfig.register(new PartieNotFoundExceptionMapper());
    }
}