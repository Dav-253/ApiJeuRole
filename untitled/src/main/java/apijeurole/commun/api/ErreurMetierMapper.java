package apijeurole.commun.api;

import apijeurole.commun.domain.CategorieErreur;
import apijeurole.commun.domain.ErreurMetier;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ErreurMetierMapper implements ExceptionMapper<ErreurMetier> {

    @Override
    public Response toResponse(ErreurMetier exception) {

        int status = HttpErrorMapping.statusPour(exception.getCategorie());

        return Response.status(status)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErreurReponse(exception.getCode(), exception.getMessage()))
                .build();
    }

}
