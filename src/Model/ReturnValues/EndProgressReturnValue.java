package Model.ReturnValues;

public class EndProgressReturnValue {
    private boolean hasProgress;

    public boolean hasProgress() {
        return hasProgress;
    }

    public EndProgressReturnValue(boolean hasProgress) {
        this.hasProgress = hasProgress;
    }
}
