package View.OthelloView;

import View.Viewable;

public class OthelloViewable extends Viewable {
    private int range;
    private String[][] board;
    private String whoseTurn;

    public String whoseTurn() {
        return whoseTurn;
    }

    public int getRange() {
        return range;
    }

    public String[][] getBoard() {
        return board;
    }
}
