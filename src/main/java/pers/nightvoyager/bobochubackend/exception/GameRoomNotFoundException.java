package pers.nightvoyager.bobochubackend.exception;

public class GameRoomNotFoundException extends GameException{
    public GameRoomNotFoundException(String message) {
        super(message);
    }

    public GameRoomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
