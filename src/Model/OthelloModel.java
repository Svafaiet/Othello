package Model;

import java.util.ArrayList;

public class OthelloModel {
    private OthelloModel() {
        progresss = new ArrayList<>();
        runningProgress = null;
    }

    public static OthelloModel getInstance() {
        return new OthelloModel();
    }

    private ArrayList<ProgressModel> progresss;
    private ArrayList<PlayerModel> players;

    public AddNewPlayerReturnValue addNewPlayer(PlayerModel player) {
        AddNewPlayerReturnValue addNewPlayerReturnValue = new AddNewPlayerReturnValue(players.contains(player));
        if (!players.contains(player)) {
            players.add(player);
        }
        return addNewPlayerReturnValue;
    }

    public AddNewProgressReturnValue addNewProgress(ProgressModel progress) {
        AddNewProgressReturnValue addNewProgressReturnValue = new AddNewProgressReturnValue();
        addNewProgressReturnValue.setProgressStatus(progresss.contains(progress));
        addNewProgressReturnValue.setPlayerStatus(players.contains(progress.getPlayer1()) && players.contains(progress.getPlayer2()));
        if (addNewProgressReturnValue.isRequestValid()) {
            progresss.add(progress);
            players.add(progress.getPlayer1());
            players.add(progress.getPlayer2());
        }
        return addNewProgressReturnValue;
    }

    public EndProgressReturnValue endProgress(ProgressModel progress) {
        EndProgressReturnValue endProgressReturnValue = new EndProgressReturnValue(progresss.contains(progress) || runningProgress.equals(progress));
        if (progresss.contains(progress)) {
            progresss.remove(progress);
        }
//        if (progress.whoWon() == PlayerType.PLAYER1) {
//            //TODO add me
//            progress.getPlayer1().win();
//            progress.getPlayer2().lose();
//        } else {
//            progress.getPlayer2().win();
//            progress.getPlayer1().lose();
//        }
        return endProgressReturnValue;
    }

    public ProgressModel findProgressByName(String progressName) {
        for (ProgressModel progress : progresss) {
            if (progress.equals(progressName)) {
                return progress;
            }
        }
        return null;
    }

    private ProgressModel runningProgress;

    public boolean isAnyProgressRunning() {
        if (runningProgress == null) {
            return false;
        }
        return true;
    }

    public void quitProgress() {
        runningProgress = null;
    }

}

class AddNewProgressReturnValue {

    private boolean hasProgress;
    private boolean hasPlayer;

    public boolean hasProgress() {
        return hasProgress;
    }

    public void setProgressStatus(boolean hasProgress) {
        this.hasProgress = hasProgress;
    }

    public boolean hasPlayer() {
        return hasPlayer;
    }

    public void setPlayerStatus(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }

    public boolean isRequestValid() {
        return !hasProgress && hasPlayer;
    }
}

class EndProgressReturnValue {
    private boolean hasProgress;

    public boolean hasProgress() {
        return hasProgress;
    }

    EndProgressReturnValue(boolean hasProgress) {
        this.hasProgress = hasProgress;
    }
}

class AddNewPlayerReturnValue {
    private boolean hasPlayer;

    public boolean hasPlayer() {
        return hasPlayer;
    }

    AddNewPlayerReturnValue(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }
}