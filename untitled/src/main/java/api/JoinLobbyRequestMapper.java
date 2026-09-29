package api;

import api.dto.JoinLobbyRequest;
import api.dto.JoinLobbyRequest;
import domain.heros.Heros;

public class JoinLobbyRequestMapper {

    public Heros toHeroDomain(JoinLobbyRequest request) {
        Heros heros = new Heros();

        heros.setId(request.herosId());
        return heros;
    }

}
