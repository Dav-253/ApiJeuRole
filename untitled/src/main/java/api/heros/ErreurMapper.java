package api.heros;

import erreur.exception.JoueurIntrouvableException;
import erreur.exception.NomDejaUtiliseException;
import jakarta.json.bind.JsonbException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ErreurMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e) {
        // Erreurs gérées par JAX-RS lui-même (route inconnue, méthode non permise...)
        if (e instanceof WebApplicationException wae) {
            return wae.getResponse();
        }
        if (e instanceof JoueurIntrouvableException) {
            return reponse(404, e.getMessage());
        }
        if (e instanceof NomDejaUtiliseException) {
            return reponse(409, e.getMessage());
        }
        if (e instanceof IllegalArgumentException) {   // nom invalide, valeur hors 3-18, espèce/classe inconnue...
            return reponse(400, e.getMessage());
        }
        for (Throwable t = e; t != null; t = t.getCause()) {
            if (t instanceof JsonbException) {
                return reponse(400, "JSON invalide.");
            }
        }
        return reponse(500, "Erreur interne du serveur.");
    }

    private static Response reponse(int statut, String message) {
        return Response.status(statut)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErreurResponse(message))
                .build();
    }
}
