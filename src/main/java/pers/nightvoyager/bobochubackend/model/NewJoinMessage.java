package pers.nightvoyager.bobochubackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NewJoinMessage {
    Integer newJoin;  // inform all players in a room that a new play is joining in with the current amount of players
}
