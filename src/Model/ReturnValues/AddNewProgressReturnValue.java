package Model.ReturnValues;

public class AddNewProgressReturnValue {

    private boolean hasProgress;
    private boolean hasPlayer;

    public boolean hasProgress() {
        return hasProgress;
    }

    public void isProgressNew(boolean hasProgress) {
        this.hasProgress = hasProgress;
    }

    public boolean hasPlayer() {
        return hasPlayer;
    }

    public void arePlayersNew(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }

    public boolean isRequestValid() {
        return !hasProgress && hasPlayer;
    }
}
