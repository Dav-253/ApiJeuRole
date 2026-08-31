package Lobby;

import Hero.Hero;

import java.util.UUID;

public class Joeur {

    private UUID id;
    private boolean isReady;

    private Hero hero;


    public UUID getId() {
        return id;
    }

    public Hero getHero() {
        return hero;
    }

}
