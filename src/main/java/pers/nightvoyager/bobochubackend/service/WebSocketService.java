package pers.nightvoyager.bobochubackend.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pers.nightvoyager.bobochubackend.model.Player;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@Component
@Service
@ServerEndpoint("/api/websocket")
public class WebSocketService {
    private final GameService gameService = new GameService();

    @OnOpen
    public void onOpen(Session session) {
        gameService.setPlayer(new Player(session));
    }

    @OnClose
    public void onClose() {
        gameService.quitRoom();
    }

    @OnMessage
    public void onMessage(Session session, String message) {

    }

    @OnError
    public void onError(Session session, Throwable e) {
        e.printStackTrace();
    }
}
