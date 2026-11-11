package apijeurole.lobby.api;

import apijeurole.commun.api.Authentification;
import apijeurole.lobby.application.LobbyService;
import apijeurole.lobby.application.LobbyVue;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.awt.*;
import java.net.URI;

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
}
