package View;

import View.Viewables.ProgressViewable;
import View.Viewables.Viewable;

public abstract class GameView extends ProgressViewable {
    public abstract void viewGame(Viewable viewable);
}
