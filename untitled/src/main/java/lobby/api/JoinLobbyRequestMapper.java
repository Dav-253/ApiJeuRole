package lobby.api;

import domain.heros.Heros;
import lobby.api.dto.JoinLobbyRequest;

public class JoinLobbyRequestMapper {

    public Heros toHeroDomain(JoinLobbyRequest request) {
        Heros heros = new Heros();

        heros.setId(request.herosId());
        return heros;
    }

}
