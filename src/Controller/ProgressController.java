package Controller;

import Controller.OthelloControllers.OthelloController;
import Model.OthelloGameLogic.OthelloModel;
import Model.ProgressModel;
import View.OthelloView.Viewables.OthelloViewable;
import View.Viewables.PlayerViewable;
import View.Viewables.ProgressViewable;

public class ProgressController {
    private ProgressModel progressModel;
    private OthelloController othelloController;
    private PlayerController playerController

    public ProgressController(ProgressModel progressModel) {
        this.progressModel = progressModel;
        othelloController = new OthelloController((OthelloModel) progressModel.getCurTurnGame());
    }

    public ProgressViewable makeShowOthelloGameViewable() {
        OthelloViewable othelloViewable = othelloController.makeOthelloBoardViewable();
        PlayerViewable playerViewable =
        ProgressViewable progressViewable = new ProgressViewable()
        (OthelloModel) progressModel.getCurTurnGame()
    }
}
