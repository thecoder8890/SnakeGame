package com.sanke.game.sahaj.service;

import com.sanke.game.sahaj.model.Board;
import com.sanke.game.sahaj.model.Player;

import java.util.*;

public class SnakeAndLadderService {
    private Board board;
    private int noOfPlayers;
    private Queue<Player> players;

    private static final int size = 100;
    private static final int maxDiceValue = 6;
    private static final int minDiceValue = 1;

    public SnakeAndLadderService(Map<Integer, Integer> snakes, Map<Integer, Integer> ladders, List<Player> players) {
        this.board = new Board(size);
        this.setSnakes(snakes);
        this.setLadders(ladders);
        this.setPlayers(players);
    }

    private void setPlayers(List<Player> players) {
        this.noOfPlayers = players.size();
        this.players = new LinkedList<>();
        Map<UUID, Integer> playerPositions = new HashMap<>();
        for(Player player : players) {
            this.players.add(player);
            playerPositions.put(player.getPlayerId(), 1);
        }
        board.setPlayerPositions(playerPositions);
    }

    private void setSnakes(Map<Integer, Integer> snakes) {
        board.setSnakes(snakes);
    }

    private void setLadders(Map<Integer, Integer> ladders) {
        board.setLadders(ladders);
    }

    private int moveThrough(int position) {
        int prePosition = -1;
        while(prePosition != position) {
            prePosition = position;
            Map<Integer, Integer> snakes = board.getSnakes();
            Map<Integer, Integer> ladders = board.getLadders();
            if(snakes.containsKey(position)) {
                position = snakes.get(position);
            }
            if(ladders.containsKey(position)) {
                position = ladders.get(position);
            }
        }
        return position;
    }

    private void move(Player player, int diceValue) {
        int position = board.getPlayerPositions().get(player.getPlayerId());
        System.out.println(player.getName() + " at " + position);
        if(position+diceValue <= board.getSize()) position += diceValue;
        position = moveThrough(position);
        board.getPlayerPositions().put(player.getPlayerId(), position);
        System.out.println(player.getName() + " moved to " + position);
    }

    private int diceRoll() {
        Random random = new Random();
        int value = random.nextInt((maxDiceValue-minDiceValue)+1) + minDiceValue;
        return value;
    }

    private boolean isFinalDestination(Player player) {
        if(board.getPlayerPositions().get(player.getPlayerId()).equals(board.getSize())) return true;
        return false;
    }

    public void startGame() {
        while(true) {
            int diceValue = diceRoll();
            Player currentPlayer = players.poll();
            move(currentPlayer, diceValue);
            if(isFinalDestination(currentPlayer)) {
                System.out.println(currentPlayer.getName() + " has won");
                board.getPlayerPositions().remove(currentPlayer.getPlayerId());
            }
            else {
                players.add(currentPlayer);
            }
            if(players.isEmpty()) break;
        }
    }
}
