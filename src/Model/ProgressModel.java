package Model;

import Model.OthelloGameLogic.OthelloModel;
import Model.ReturnValues.MakeMoveReturnValue;

import java.util.ArrayList;

public class ProgressModel {

    private ProgressModel(){
    }

    public static ProgressModel makeProgress(String progressName, String[] players) {
        ProgressModel newProgress = new ProgressModel();
        newProgress.progressName = progressName;
        for (String playerName : players) {
            newProgress.players.add(new PlayerModel(playerName));
        }
        newProgress.curTurnGame = new OthelloModel(); //TODO change here for multi game support
        return newProgress;
    }

    private String progressName;
    private ArrayList<PlayerModel> players;
    private boolean hasUndone = true;
    private boolean isGameFinished = false;
    //with a little inheritance we can have this program for all kind of twoPlayerprogresss
    private GameModel curTurnGame;
    private GameModel lastTurnGame;

    public GameModel getCurTurnGame() {
        return curTurnGame;
    }

    public ArrayList<PlayerModel> getPlayers() {
        return players;
    }

    public String getProgressName() {
        return progressName;
    }

    public MakeMoveReturnValue makeMove(MoveModel move) {
        if(curTurnGame.isMoveValid(move)) {
            lastTurnGame = curTurnGame.clone();
            curTurnGame.makeMove(move);
            hasUndone = false;
            if (curTurnGame.isGameFinished()) {
                isGameFinished = true;
                return new MakeMoveReturnValue( true);
            } else {
                return new MakeMoveReturnValue( true);
            }
        }
        return new MakeMoveReturnValue(false);
    }

    public boolean equals(String progressName) {
        return this.progressName.equals(progressName);
    }

    public boolean isUndoValid() {
        if(hasUndone) {
            return false;
        }
        if(isGameFinished) {
            return false;
            //TODO can be changed
        }
        return players.get(curTurnGame.whoseTurnItIs()).isThereAnyUndo();
    }

    public void useUndo() {
        players.get(curTurnGame.whoseTurnItIs()).useUndo();
        hasUndone = true;
        curTurnGame = lastTurnGame;
    }

    public PlayerModel whoseTurnItIs() {
        return players.get(curTurnGame.whoseTurnItIs());
    }
}

