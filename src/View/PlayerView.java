package View;

import View.Viewables.PlayerViewable;
import View.Viewables.Viewable;

import javax.swing.text.View;

public class PlayerView {

    public void showTurn(Viewable viewable) {
        PlayerViewable playerViewable = (PlayerViewable) viewable;
        System.out.println(playerViewable.getPlayerName() + ":");
    }

    public void showWiningPlayer(Viewable viewable) {
        PlayerViewable playerViewable = (PlayerViewable) viewable;
        System.out.println(playerViewable.getPlayerName() + " won");
    }

}
