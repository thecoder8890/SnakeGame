package com.sanke.game.sahaj.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Board {
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;
    private int size;
    private Map<UUID, Integer> playerPositions;


    public Board(int size) {
        this.size = size;
        this.ladders = new HashMap<>();
        this.snakes = new HashMap<>();
        this.playerPositions = new HashMap<>();
    }
    public int getSize() {
        return size;
    }

    public Map<Integer, Integer> getSnakes() {
        return snakes;
    }

    public void setSnakes(Map<Integer, Integer> snakes) {
        this.snakes = snakes;
    }

    public Map<Integer, Integer> getLadders() {
        return ladders;
    }

    public void setLadders(Map<Integer, Integer> ladders) {
        this.ladders = ladders;
    }

    public Map<UUID, Integer> getPlayerPositions() {
        return playerPositions;
    }

    public void setPlayerPositions(Map<UUID, Integer> playerPositions) {
        this.playerPositions = playerPositions;
    }
}
