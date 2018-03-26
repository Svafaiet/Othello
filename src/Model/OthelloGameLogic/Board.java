package Model.OthelloGameLogic;

import Model.GameModel;

public class Board extends GameModel {
    private Cell[][] cells;
    Board() {
        cells = new Cell[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                cells[i][j] = Cell.CELL_EMPTY;
            }
        }
    }

    @Override
    public Board clone() {
        Board newBoard = new Board();
        for (int i = 0; i < 6; i++) {
            System.arraycopy(cells[i], 0, newBoard.cells[i], 0, 6);
        }
        return newBoard;
    }

    public Cell at(int i, int j) {
        return cells[i][j];
    }
}
