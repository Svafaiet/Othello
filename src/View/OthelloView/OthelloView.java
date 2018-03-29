package View.OthelloView;

import View.*;
import View.OthelloView.Viewables.OthelloViewable;
import View.Viewables.Viewable;

public class OthelloView extends GameView {
    @Override
    public void viewGame(Viewable viewable) {
        OthelloViewable othelloViewable = (OthelloViewable) viewable;
        for (int i = 0; i < othelloViewable.getRange(); i++) {
            for (int j = 0; j < othelloViewable.getRange(); j++) {
                System.out.print(othelloViewable.getBoard()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(othelloViewable.whoseTurn() + ":");
    }
}
