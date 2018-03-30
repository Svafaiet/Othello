package Model.ReturnValues;

public class AddNewPlayerReturnValue {
    private boolean hasPlayer;

    public boolean hasPlayer() {
        return hasPlayer;
    }

    public AddNewPlayerReturnValue(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }
}
