package apijeurole.joueur.api;

import apijeurole.commun.domain.RequeteInvalideException;
import apijeurole.joueur.application.JoueurService;
import apijeurole.joueur.domain.Joueur;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/players")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JoueurResource {

    private final JoueurService joueurService;

    public JoueurService getJoueurService() {
        this.joueurService = joueurService;
    }

    @POST
    public Response creerCompte(InscriptionRequest requete) {
        if (requete == null) {
            throw new RequeteInvalideException("Le corps de la requête est obligatoire.");
        }
        Joueur joueur = joueurService.inscrire(requete.pseudonym, requete.email, requete.password);
        return Response.created(URI.create("/players/" + joueur.getId()))
                .entity(JoueurResponse.de(joueur))
                .build();
    }
}
