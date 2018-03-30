package Model.ReturnValues;

public class LoadProgressReturnValue {
    private boolean hasGame;
    private boolean anyGameRunning;

    public boolean isAnyGameRunning() {
        return anyGameRunning;
    }

    public boolean hasGame() {
        return hasGame;
    }

    public LoadProgressReturnValue(boolean hasGame, boolean anyGameRunning) {
        this.hasGame = hasGame;
        this.anyGameRunning = anyGameRunning;
    }
}
