package partie.api;


import partie.domain.exception.PartieIntrouvableException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class PartieNotFoundExceptionMapper implements ExceptionMapper<PartieIntrouvableException> {

    @Override
    public Response toResponse(PartieIntrouvableException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .type(MediaType.TEXT_PLAIN)
                .entity(exception.getMessage())
                .build();
    }
}
