package Controller;

import Model.PlayerModel;
import View.ProgramRequestType;
import View.Viewables.PlayerViewable;

public class PlayerController {
    private String playerName;

    public PlayerViewable makePlayerViewable() {
        return new PlayerViewable(playerName);
    }

    public PlayerViewable makeShowWinnerViewable() {
        PlayerViewable playerViewable = new PlayerViewable(playerName);
        playerViewable.setRequestType(ProgramRequestType.SHOW_WINNER);
        return playerViewable;
    }

    public PlayerController(String playerName) {
        this.playerName = playerName;
    }
}
