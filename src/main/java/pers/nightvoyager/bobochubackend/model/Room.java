package pers.nightvoyager.bobochubackend.model;

import java.util.concurrent.CopyOnWriteArrayList;

public class Room {
    private int number;
    private CopyOnWriteArrayList<Player> players = new CopyOnWriteArrayList<>();

    public Room() {
    }

    public Room(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Room setNumber(int number) {
        this.number = number;
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
