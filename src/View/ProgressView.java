package View;

import View.OthelloView.OthelloView;
import View.Viewables.ProgressViewable;
import View.Viewables.Viewable;

public class ProgressView {
    private PlayerView playerView = new PlayerView();
    private OthelloView othelloView = new OthelloView();

    public void showProgress(Viewable veiwable) {
        ProgressViewable progressViewable = (ProgressViewable) veiwable;
        StringBuilder progressView = new StringBuilder(progressViewable.getGameName());
        for (String player : progressViewable.getPlayers()) {
            progressView.append(" ").append(player);
        }
        System.out.println(progressView.toString());
    }

    public void showGame(Viewable viewable) {
        ProgressViewable progressViewable = (ProgressViewable) viewable;
        othelloView.viewGame(progressViewable.getOthelloViewable());
        playerView.showTurn(progressViewable.getPlayerViewable());
    }

    private void showOthelloGame(Viewable viewable) {
        othelloView.viewGame(viewable);
    }

    public void showWiningPlayer(Viewable viewable) {
        playerView.showWiningPlayer(((ProgressViewable) viewable).getPlayerViewable());
    }
}
