package Model;

public class UndoReturnValue {
    private boolean canUndo = true;

    public void canNotUndo() {
        canUndo = false;
    }

    public boolean isUndoValid() {
        return canUndo;
    }
}
