package Model.ReturnValues;

public class MakeMoveReturnValue {
    private boolean isMoveValid = true;

    public MakeMoveReturnValue(boolean isMoveValid) {
        this.isMoveValid = isMoveValid;
    }

    public boolean isMoveValid() {
        return isMoveValid;
    }
}
