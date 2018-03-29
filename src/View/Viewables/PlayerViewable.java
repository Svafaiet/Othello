package View.Viewables;

import View.Viewables.Viewable;

public class PlayerViewable extends Viewable {
    private String playerName;

    public PlayerViewable(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
