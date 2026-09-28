package main.api;

import main.api.dto.JoinLobbyRequests;
import main.domain.hero.Hero;

public class JoinLobbyRequestMapper {

    public Hero toHeroDomain(JoinLobbyRequests request) {
        Hero hero = new Hero();

        hero.setId(request.heroId());
        return hero;
    }

}
