package Controller.OthelloControllers;

import Model.OthelloGameLogic.OthelloModel;
import Model.OthelloGameLogic.OthelloMoveModel;
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

    public OthelloController(){
    }

    public OthelloMoveModel makeOthelloMove(String move) {
        OthelloMoveModel othelloMoveModel = new OthelloMoveModel(findX(move), findY(move));
        return othelloMoveModel;
    }

    public static int findY(String move) {
        return move.charAt(0) - 'A';
    }

    public static int findX(String move) {
        return move.charAt(1) - '1';
    }
}
