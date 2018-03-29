package Model;

/** only support games with undo */

public class PlayerModel {
    private static final int UNDO_COUNT = 3;
    private String playerName;
    private int undoRemainingCount;

    PlayerModel (String playerName) {
        this.playerName = playerName;
        undoRemainingCount = UNDO_COUNT;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isThereAnyUndo() {
        return undoRemainingCount > 0;
    }

    public void useUndo() {
        if(isThereAnyUndo()) {
            undoRemainingCount--;
        }
    }

}
