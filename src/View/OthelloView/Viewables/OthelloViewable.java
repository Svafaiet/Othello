package View.OthelloView.Viewables;

import View.Viewables.Viewable;

public class OthelloViewable extends Viewable {
    private int range;
    private String[][] board;

    public OthelloViewable(String[][] board, int range) {
        this.board = board;
        this.range = range;
    }


    public int getRange() {
        return range;
    }

    public String[][] getBoard() {
        return board;
    }
}
