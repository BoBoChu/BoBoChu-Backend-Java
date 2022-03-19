package pers.nightvoyager.bobochubackend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pers.nightvoyager.bobochubackend.model.Player;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@Component
@Slf4j
@Service
@ServerEndpoint("/api/websocket")
public class WebSocketService {
    private final GameService gameService = new GameService();

    @OnOpen
    public void onOpen(Session session) {
        log.info("A new connection is established");
        gameService.setPlayer(new Player(session));
    }

    @OnClose
    public void onClose() {
        gameService.quitRoom();
        log.info("A connection is closed");
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("Receive message: " + message);
    }

    @OnError
    public void onError(Session session, Throwable e) {
        e.printStackTrace();
    }
}
