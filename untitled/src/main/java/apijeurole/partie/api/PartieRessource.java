package apijeurole.partie.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import apijeurole.partie.application.ConsulterPartieQuery;
import apijeurole.partie.application.ConsulterPartieUseCase;

import java.util.UUID;

@Path("/parties")
@Produces(MediaType.APPLICATION_JSON)
public class PartieRessource {

    private final ConsulterPartieUseCase consulterPartieUseCase;
    private final PartieResponseMapper responseMapper;

    public PartieRessource(ConsulterPartieUseCase consulterPartieUseCase, PartieResponseMapper responseMapper) {
        this.consulterPartieUseCase = consulterPartieUseCase;
        this.responseMapper = responseMapper;
    }


    @GET
    @Path("{partieId}")
    public Response consulterPartie(@PathParam("partieId") UUID partieId) {
        return Response.ok(responseMapper.toResponse(consulterPartieUseCase.execute(new ConsulterPartieQuery(partieId))))
                .build();
    }
}
