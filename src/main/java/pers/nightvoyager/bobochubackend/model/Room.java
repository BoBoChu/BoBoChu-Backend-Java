package pers.nightvoyager.bobochubackend.model;

import java.util.concurrent.CopyOnWriteArrayList;

public class Room {
    private int number;
    private String difficulty;
    private CopyOnWriteArrayList<Player> players = new CopyOnWriteArrayList<>();

    public Room() {
    }

    public int getNumber() {
        return number;
    }

    public Room setNumber(int number) {
        this.number = number;
        return this;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Room setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public CopyOnWriteArrayList<Player> getPlayers() {
        return players;
    }

    public Room setPlayers(CopyOnWriteArrayList<Player> players) {
        this.players = players;
        return this;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public boolean contains(Player player) {
        return players.contains(player);
    }
}
