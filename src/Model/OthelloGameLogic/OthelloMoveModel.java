package Model.OthelloGameLogic;

import Model.MoveModel;
import Model.PlayerType;

public class OthelloMoveModel extends MoveModel {
    private int x;
    private int y;

    public OthelloMoveModel(){
    }

    public OthelloMoveModel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
