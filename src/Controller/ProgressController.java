package Controller;

import Controller.OthelloControllers.OthelloController;
import Model.MoveModel;
import Model.OthelloGameLogic.OthelloModel;
import Model.OthelloGameLogic.OthelloMoveModel;
import Model.PlayerModel;
import Model.ProgressModel;
import View.OthelloView.Viewables.OthelloViewable;
import View.ProgramRequestType;
import View.ProgressView;
import View.Viewables.PlayerViewable;
import View.Viewables.ProgressViewable;

import java.util.ArrayList;

public class ProgressController {
    private ProgressModel progressModel;
    private OthelloController othelloController;
    private PlayerController playerController;

    public ProgressController(ProgressModel progressModel) {
        this.progressModel = progressModel;
        othelloController = new OthelloController((OthelloModel) progressModel.getCurTurnGame());
        playerController = new PlayerController(progressModel.whoseTurnItIs().getPlayerName());
    }

    public ProgressViewable makeShowOthelloGameViewable() {
        OthelloViewable othelloViewable = othelloController.makeOthelloBoardViewable();
        PlayerViewable playerViewable = playerController.makePlayerViewable();
        ProgressViewable progressViewable = new ProgressViewable(playerViewable, othelloViewable);
        progressViewable.setRequestType(ProgramRequestType.SHOW_OTHELLO_GAME);
        return progressViewable;
    }

    public ProgressViewable makeShowProgressViewable() {
        ArrayList<String> playerNames = new ArrayList<>();
        for (PlayerModel playerModel : progressModel.getPlayers()) {
            playerNames.add(playerModel.getPlayerName());
        }
        ProgressViewable progressViewable = new ProgressViewable(playerNames, progressModel.getProgressName());
        progressViewable.setRequestType(ProgramRequestType.SHOW_PROGRESS);
        return progressViewable;
    }

    public MoveModel makeMoveForGame(String requestMove) {
        return new OthelloController().makeOthelloMove(requestMove);
    }
}
