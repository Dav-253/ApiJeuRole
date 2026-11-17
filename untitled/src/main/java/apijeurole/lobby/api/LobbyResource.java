package apijeurole.lobby.api;
import apijeurole.commun.api.Authentification;
import apijeurole.commun.domain.RequeteInvalideException;
import apijeurole.lobby.application.LobbyService;
import apijeurole.lobby.application.LobbyVue;
import apijeurole.lobby.application.PartieLancee;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import java.net.URI;
import java.util.List;
import java.util.UUID;
@Path("/lobbies")
@Produces(MediaType.APPLICATION_JSON)
public class LobbyResource {
    private final LobbyService lobbyService;
    public LobbyResource(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }
    @POST
    public Response creer(@Context SecurityContext contexte) {
        LobbyVue lobby = lobbyService.creerLobby(Authentification.joueurId(contexte));
        return Response.created(URI.create("/lobbies/" + lobby.id()))
                .entity(LobbyResponse.de(lobby))
                .build();
    }
    @GET
    public List<LobbyResumeResponse> lister() {
        return lobbyService.listerLobbysRejoignables().stream().map(LobbyResumeResponse::de).toList();
    }
    @GET
    @Path("{lobbyId}")
    public LobbyResponse consulter(@PathParam("lobbyId") UUID lobbyId) {
        return LobbyResponse.de(lobbyService.consulterLobby(lobbyId));
    }
    @POST
    @Path("{lobbyId}/players")
    public Response rejoindre(@PathParam("lobbyId") UUID lobbyId, @Context SecurityContext contexte) {
        UUID joueurId = Authentification.joueurId(contexte);
        LobbyVue lobby = lobbyService.rejoindreLobby(lobbyId, joueurId);
        return Response.created(URI.create("/lobbies/" + lobbyId + "/players/" + joueurId))
                .entity(LobbyResponse.de(lobby))
                .build();
    }
    /** Un joueur ne peut retirer que lui-même : sinon 403 FORBIDDEN. */
    @DELETE
    @Path("{lobbyId}/players/{playerId}")
    public Response quitter(@PathParam("lobbyId") UUID lobbyId, @PathParam("playerId") UUID playerId,
                            @Context SecurityContext contexte) {
        Authentification.exigerMemeJoueur(contexte, playerId);
        lobbyService.quitterLobby(lobbyId, playerId);
        return Response.noContent().build();
    }
    /** Un joueur ne choisit que pour lui-même : sinon 403 FORBIDDEN. */
    @PUT
    @Path("{lobbyId}/players/{playerId}/hero")
    @Consumes(MediaType.APPLICATION_JSON)
    public LobbyResponse choisirHeros(@PathParam("lobbyId") UUID lobbyId, @PathParam("playerId") UUID playerId,
                                      ChoixHerosRequest requete, @Context SecurityContext contexte) {
        Authentification.exigerMemeJoueur(contexte, playerId);
        if (requete == null || requete.heroId == null || requete.heroId.isBlank()) {
            throw new RequeteInvalideException("Le champ heroId est obligatoire.");
        }
        return LobbyResponse.de(lobbyService.choisirHeros(lobbyId, playerId, lireUuid(requete.heroId)));
    }
    private static UUID lireUuid(String valeur) {
        try {
            return UUID.fromString(valeur);
        } catch (IllegalArgumentException e) {
            throw new RequeteInvalideException("Le champ heroId n'est pas un identifiant valide.");
        }
    }
    /** Seul l'hôte peut lancer la partie. */
    @POST
    @Path("{lobbyId}/game")
    public Response lancerPartie(@PathParam("lobbyId") UUID lobbyId, @Context SecurityContext contexte) {
        PartieLancee partie = lobbyService.lancerPartie(lobbyId, Authentification.joueurId(contexte));
        return Response.created(URI.create("/games/" + partie.id()))
                .entity(PartieLanceeResponse.de(partie))
                .build();
    }
}