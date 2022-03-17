package pers.nightvoyager.bobochubackend.model;

import javax.websocket.Session;

public class Player {
    private Session session;

    public Player() {
    }

    public Player(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public Player setSession(Session session) {
        this.session = session;
        return this;
    }
}
