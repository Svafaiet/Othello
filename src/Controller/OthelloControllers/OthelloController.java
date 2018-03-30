package Controller.OthelloControllers;

import Model.OthelloGameLogic.OthelloModel;
import View.OthelloView.Viewables.OthelloViewable;

public class OthelloController {
    private OthelloModel othelloModel;

    public OthelloViewable makeOthelloBoardViewable() {
        int range = othelloModel.RANGE;
        String[][] gameBoard = new String[range][range];
        for (int i = 0; i < range; i++) {
            for (int j = 0; j < range; j++) {
                switch (othelloModel.at(i, j)) {
                    case CELL_EMPTY:
                        gameBoard[i][j] = "-";
                        break;
                    case CELL_PLAYER1:
                        gameBoard[i][j] = "1";
                        break;
                    case CELL_PLAYER2:
                        gameBoard[i][j] = "0";
                        break;
                }
            }
        }
        return new OthelloViewable(gameBoard, range);
    }

    public OthelloController(OthelloModel othelloModel) {
        this.othelloModel = othelloModel;
    }
}
