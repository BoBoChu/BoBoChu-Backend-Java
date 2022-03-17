package pers.nightvoyager.bobochubackend.service;

import org.springframework.stereotype.Service;
import pers.nightvoyager.bobochubackend.exception.GameCannotCreateRoomException;
import pers.nightvoyager.bobochubackend.exception.GameRoomNotFoundException;
import pers.nightvoyager.bobochubackend.model.Player;
import pers.nightvoyager.bobochubackend.model.Room;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {
    // thread-save hash map, used to save the room objects
    private static ConcurrentHashMap<Integer, Room> rooms = new ConcurrentHashMap<>();

    public int createNewRoom() {
        if (rooms.size() == 1000000)
            throw new GameCannotCreateRoomException("The amount of rooms reaches the upper limit.");

        int roomNumber = (int) (Math.random() * 1000000);
        while (rooms.containsKey(roomNumber))
            roomNumber = (int) (Math.random() * 1000000);

        Room room = new Room(roomNumber);
        rooms.put(roomNumber, room);

        return roomNumber;
    }

    public void destroyRoom(int roomNumber) {
        rooms.remove(roomNumber);
    }

    public void joinRoom(int roomNumber, Player player) {
        if (!rooms.containsKey(roomNumber))
            throw new GameRoomNotFoundException("Room with number " + roomNumber + " is not found.");
        else
            rooms.get(roomNumber).addPlayer(player);
    }

    public void quitRoom(Player player) {
//        rooms.forEach((key, value) -> {
//            if (value.contains(player))
//                value.removePlayer(player);
//        });
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            if (entry.getValue().contains(player)) {
                entry.getValue().removePlayer(player);
                return;
            }
        }
    }
}
