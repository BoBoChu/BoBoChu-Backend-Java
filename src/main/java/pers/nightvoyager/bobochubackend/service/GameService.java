package pers.nightvoyager.bobochubackend.service;

import org.springframework.stereotype.Service;
import pers.nightvoyager.bobochubackend.exception.GameCannotCreateRoomException;
import pers.nightvoyager.bobochubackend.exception.GameRoomNotFoundException;
import pers.nightvoyager.bobochubackend.model.Player;
import pers.nightvoyager.bobochubackend.model.Room;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {
    // thread-save hash map, used to save the room objects
    private static final ConcurrentHashMap<Integer, Room> rooms = new ConcurrentHashMap<>();

    private Room room;

    private Player player;

    public GameService setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public Player getPlayer() {
        return player;
    }

    public GameService setRoom(Room room) {
        this.room = room;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public Room getRoom(int roomNumber) {
        return rooms.get(roomNumber);
    }

    public int createAndJoinRoom(String difficulty) {
        int roomNumber = createNewRoom(difficulty);
        joinRoom(roomNumber);
        return roomNumber;
    }

    public int createNewRoom(String difficulty) {
        // throw exception if no new room can be created
        if (rooms.size() == 1000000)
            throw new GameCannotCreateRoomException("The amount of rooms reaches the upper limit.");

        // generate a six-bit room number randomly
        int roomNumber = (int) (Math.random() * 1000000);
        while (rooms.containsKey(roomNumber))
            roomNumber = (int) (Math.random() * 1000000);

        // instantiate a room and put it in the ConcurrentHashMap
        room = new Room().setNumber(roomNumber).setDifficulty(difficulty);
        rooms.put(roomNumber, room);

        return roomNumber;
    }

    public void destroyRoom() {
        destroyRoom(room.getNumber());
    }

    public void destroyRoom(int roomNumber) {
        rooms.remove(roomNumber);
        room = null;
    }

    public void joinRoom(int roomNumber) {
        joinRoom(roomNumber, player);
    }

    public void joinRoom(int roomNumber, Player player) {
        // throw exception if no room with given room number exists
        if (!rooms.containsKey(roomNumber))
            throw new GameRoomNotFoundException("Room with number " + roomNumber + " is not found.");

        // get the room to join
        room = rooms.get(roomNumber);
        // register the player to the room
        room.addPlayer(player);
    }

    public void quitRoom() {
        quitRoom(player);
    }

    public void quitRoom(Player player) {
        if (room != null) {
            // remove the player from the room
            room.removePlayer(player);

            // if the last player in the room quits and thus the room becomes empty, destroy the room
            if (room.getPlayers().size() == 0)
                destroyRoom();

            room = null;
        }
    }
}
