package apijeurole.partie.api;

import apijeurole.partie.domain.exception.PartieException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class PartieExceptionMapper implements ExceptionMapper<PartieException> {

    private static final int UNPROCESSABLE_CONTENT = 422;

    @Override
    public Response toResponse(PartieException exception) {
        return Response.status(UNPROCESSABLE_CONTENT)
                .type(MediaType.TEXT_PLAIN)
                .entity(exception.getMessage())
                .build();
    }
}
