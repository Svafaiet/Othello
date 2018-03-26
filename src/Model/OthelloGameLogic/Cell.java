package Model.OthelloGameLogic;

import Model.PlayerType;

public enum Cell {
    CELL_PLAYER1, CELL_PLAYER2, CELL_EMPTY
}

class CellFunctions {
    public static Cell oppositeCellType(Cell cell) {
        if(cell == Cell.CELL_PLAYER1) {
            return Cell.CELL_PLAYER2;
        }
        if(cell == Cell.CELL_PLAYER2) {
            return Cell.CELL_PLAYER1;
        }
        return Cell.CELL_EMPTY;
    }

    public static Cell oppositeCellType(PlayerType playerType) {
        if(playerType == PlayerType.PLAYER1) {
            return Cell.CELL_PLAYER2;
        }
        if(playerType == PlayerType.PLAYER2) {
            return Cell.CELL_PLAYER1;
        }
        return Cell.CELL_EMPTY;
    }

    public static Cell cellType(PlayerType playerType) {
        if(playerType == PlayerType.PLAYER1) {
            return Cell.CELL_PLAYER1;
        }
        if(playerType == PlayerType.PLAYER2) {
            return Cell.CELL_PLAYER2;
        }
        return Cell.CELL_EMPTY;
    }

}
