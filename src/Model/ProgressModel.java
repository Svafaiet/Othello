package Model;

import java.util.ArrayList;

public class ProgressModel {
    private String progressName;
    private ArrayList<AccountModel> players;
    private boolean hasUndone;
    //with a little inheritance we can have this program for all kind of twoPlayerprogresss
    private GameModel curTurnGame;
    private GameModel lastTurnGame;

    public GameModel getCurTurnGame() {
        return curTurnGame;
    }

    public ArrayList<AccountModel> getPlayers() {
        return players;
    }

    public MakeMoveReturnValue makeMove(MoveModel move) {
        if(curTurnGame.isMoveValid(move)) {
            lastTurnGame = curTurnGame.clone();
            curTurnGame.makeMove(move);
            if (curTurnGame.isGameFinished()) {
                return new MakeMoveReturnValue(true, true, curTurnGame.whoWon());
            } else {
                return new MakeMoveReturnValue(false, true, PlayerType.NO_ONE);
            }
        }
        return new MakeMoveReturnValue(false, false, PlayerType.NO_ONE);
    }

    public boolean equals(String progressName) {
        return this.progressName.equals(progressName);
    }


}

class MakeMoveReturnValue {
    private boolean isGameFinished = false;
    private boolean isMoveValid = true;
    private PlayerType winner;

    public MakeMoveReturnValue(boolean isGameFinished, boolean isMoveValid, PlayerType winner) {
        this.isGameFinished = isGameFinished;
        this.isMoveValid = isMoveValid;
        this.winner = winner;
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public boolean isMoveValid() {
        return isMoveValid;
    }

    public PlayerType getWinner() {
        return winner;
    }
}