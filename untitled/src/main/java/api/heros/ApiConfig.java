package api.heros;

import application.heros.HerosService;
import org.glassfish.jersey.server.ResourceConfig;

public class ApiConfig extends ResourceConfig {

    public ApiConfig(HerosService herosService) {
        register(new HerosResource(herosService));
        register(ErreurMapper.class);
    }
}