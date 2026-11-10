package apijeurole.joueur.api;


import apijeurole.commun.domain.RequeteInvalideException;
import apijeurole.joueur.application.JoueurService;
import apijeurole.joueur.application.SessionOuverte;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/sessions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SessionResource {

    private final JoueurService joueurService;

    public SessionResource(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    @POST
    public Response seConnecter(ConnexionRequest requete) {
        if (requete == null) {
            throw new RequeteInvalideException("Le corps de la requête est obligatoire.");
        }

        SessionOuverte session = joueurService.connecter(requete.email, requete.password);

        return Response.status(201)
                .entity(new SessionResponse(session.joueurId().toString(), session.jeton()))
                .build();
    }

}
