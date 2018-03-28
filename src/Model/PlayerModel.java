package Model;

public class PlayerModel {
    private static final int UNDO_COUNT = 3;
    private String playerName;
    private int undoRemainingCount;

    PlayerModel (AccountModel accountModel) {
        playerName = accountModel.getAccountName();
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
