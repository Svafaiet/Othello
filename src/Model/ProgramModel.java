package Model;

import java.util.ArrayList;

public class ProgramModel {
    private ProgramModel() {
        liveProgresses = new ArrayList<>();
        runningProgress = null;
    }

    public static ProgramModel getInstance() {
        return new ProgramModel();
    }

    private ArrayList<ProgressModel> liveProgresses;
    private ArrayList<PlayerModel> players;
    private ProgressModel runningProgress;

    public AddNewPlayerReturnValue addNewPlayer(PlayerModel player) {
        AddNewPlayerReturnValue addNewPlayerReturnValue = new AddNewPlayerReturnValue(players.contains(player));
        if (!players.contains(player)) {
            players.add(player);
        }
        return addNewPlayerReturnValue;
    }

    public AddNewProgressReturnValue addNewProgress(ProgressModel progress) {
        AddNewProgressReturnValue addNewProgressReturnValue = new AddNewProgressReturnValue();
        addNewProgressReturnValue.setProgressStatus(liveProgresses.contains(progress));
        addNewProgressReturnValue.setPlayerStatus(players.contains(progress.getPlayer1()) && players.contains(progress.getPlayer2()));
        if (addNewProgressReturnValue.isRequestValid()) {
            liveProgresses.add(progress);
            players.add(progress.getPlayer1());
            players.add(progress.getPlayer2());
        }
        return addNewProgressReturnValue;
    }

    public EndProgressReturnValue endProgress(ProgressModel progress) {
        EndProgressReturnValue endProgressReturnValue = new EndProgressReturnValue(liveProgresses.contains(progress));
        if (liveProgresses.contains(progress)) {
            liveProgresses.remove(progress);
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

    public String showProgresses() {
        StringBuilder ans = new StringBuilder();
        for (ProgressModel progress : liveProgresses) {
            ans.append(progress).append("\n");
        }
        return ans.toString();
    }

    private ProgressModel findProgressByName(String progressName) {
        for (ProgressModel progress : liveProgresses) {
            if (progress.equals(progressName)) {
                return progress;
            }
        }
        return null;
    }

    public LoadProgressReturnValue loadProgress (String progressName) {
        LoadProgressReturnValue loadProgressReturnValue =
                new LoadProgressReturnValue(findProgressByName(progressName) != null, isAnyProgressRunning());
        runningProgress = findProgressByName(progressName);
        return loadProgressReturnValue;
    }

    private boolean isAnyProgressRunning() {
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

    public EndProgressReturnValue(boolean hasProgress) {
        this.hasProgress = hasProgress;
    }
}

class AddNewPlayerReturnValue {
    private boolean hasPlayer;

    public boolean hasPlayer() {
        return hasPlayer;
    }

    public AddNewPlayerReturnValue(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }
}

class LoadProgressReturnValue {
    private boolean hasGame;
    private boolean anyGameRunning;

    public boolean isAnyGameRunning() {
        return anyGameRunning;
    }

    public boolean hasGame() {
        return hasGame;
    }

    public LoadProgressReturnValue(boolean hasGame, boolean anyGameRunning) {
        this.hasGame = hasGame;
        this.anyGameRunning = anyGameRunning;
    }
}