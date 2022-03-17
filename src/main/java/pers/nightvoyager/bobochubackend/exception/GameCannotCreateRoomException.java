package pers.nightvoyager.bobochubackend.exception;

public class GameCannotCreateRoomException extends GameException{
    public GameCannotCreateRoomException(String message) {
        super(message);
    }

    public GameCannotCreateRoomException(String message, Throwable cause) {
        super(message, cause);
    }
}
