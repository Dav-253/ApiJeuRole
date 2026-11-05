package heros.api;

import heros.application.HerosService;
import heros.domain.Heros;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.UUID;

@Path("/joueurs/{joueurId}/heros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HerosResource {

    private final HerosService herosService;

    public HerosResource(HerosService herosService) {
        this.herosService = herosService;
    }

    @POST
    public Response creer(@PathParam("joueurId") UUID joueurId, CreerHerosRequest requete) {
        if (requete == null) {
            throw new IllegalArgumentException("Le corps de la requête est obligatoire.");
        }
        exiger(requete.nom, "Le nom est obligatoire.");
        exiger(requete.espece, "L'espèce est obligatoire.");
        exiger(requete.classe, "La classe est obligatoire.");
        exiger(requete.caracteristiques, "Les six caractéristiques sont obligatoires.");

        Heros heros = herosService.creerHeros(joueurId, requete.nom, requete.espece,
                requete.classe, requete.caracteristiques);

        return Response.created(URI.create("/heros/" + heros.getId()))
                .entity(HerosResponse.de(heros))
                .build();
    }

    private static void exiger(Object valeur, String message) {
        if (valeur == null) {
            throw new IllegalArgumentException(message);
        }
    }
}