package Model.OthelloGameLogic;

import Model.MoveModel;
import Model.PlayerType;

public class OthelloMoveModel extends MoveModel {
    private int x;
    private int y;

    public OthelloMoveModel(){
    }

    public OthelloMoveModel(PlayerType playerType){
        this.playerType = playerType;
    }

    public OthelloMoveModel(PlayerType playerType, int x, int y) {
        this.x = x;
        this.y = y;
        this.playerType = playerType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
