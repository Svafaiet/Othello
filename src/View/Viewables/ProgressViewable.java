package View.Viewables;

import View.OthelloView.OthelloView;
import View.OthelloView.Viewables.OthelloViewable;

import java.util.ArrayList;

public class ProgressViewable extends Viewable {
    private PlayerViewable playerViewable;
    private OthelloViewable othelloViewable;
    private ArrayList<String> players;
    private String gameName;

    public PlayerViewable getPlayerViewable() {
        return playerViewable;
    }

    public OthelloViewable getOthelloViewable() {
        return othelloViewable;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public String getGameName() {
        return gameName;
    }

    public ProgressViewable(PlayerViewable playerViewable) {
        this.playerViewable = playerViewable;
    }

    public ProgressViewable(PlayerViewable playerViewable, OthelloViewable othelloViewable) {
        this.playerViewable = playerViewable;
        this.othelloViewable = othelloViewable;
    }

    public ProgressViewable(ArrayList<String> players, String gameName) {
        this.players = players;
        this.gameName = gameName;
    }

    public ProgressViewable(){
    }
}
