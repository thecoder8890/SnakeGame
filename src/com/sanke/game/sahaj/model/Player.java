package com.sanke.game.sahaj.model;

import java.util.UUID;

public class Player {
    private String name;
    private UUID playerId;

    public Player(String name) {
        this.name = name;
        playerId = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public UUID getPlayerId() {
        return playerId;
    }
}
