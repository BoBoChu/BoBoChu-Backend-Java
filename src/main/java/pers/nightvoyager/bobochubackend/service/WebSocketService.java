package pers.nightvoyager.bobochubackend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pers.nightvoyager.bobochubackend.model.Player;
import pers.nightvoyager.bobochubackend.model.RoomNumberMessage;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
@Service
@ServerEndpoint("/api/websocket")
public class WebSocketService {
    private final GameService gameService = new GameService();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        log.info("A new connection is established");
        gameService.setPlayer(new Player(session));
        sendMessage("Successfully connected");
    }

    @OnClose
    public void onClose() {
        gameService.quitRoom();
        log.info("A connection is closed");
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        log.info("Receive message: " + message);

        Map<String, Object> messageMap = objectMapper.readValue(message, new TypeReference<Map<String, Object>>() {});

        if (messageMap.containsKey("createRoom"))
            sendMessage(objectMapper.writeValueAsString(new RoomNumberMessage(gameService.createNewRoom())));

    }

    @OnError
    public void onError(Session session, Throwable e) {
        e.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        gameService.getPlayer().getSession().getBasicRemote().sendText(message);
    }

    public void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    public void sendMessage(Player player, String message) throws IOException {
        player.getSession().getBasicRemote().sendText(message);
    }
}
