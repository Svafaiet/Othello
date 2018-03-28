package Model;

import java.util.ArrayList;

public class ProgramModel {
    private ProgramModel() {
        notFinishedProgresses = new ArrayList<>();
        runningProgress = null;
    }

    public static ProgramModel getInstance() {
        return new ProgramModel();
    }

    private ArrayList<ProgressModel> notFinishedProgresses;
    private ArrayList<AccountModel> acounts;
    private ProgressModel runningProgress;

    public AddNewPlayerReturnValue addNewPlayer(AccountModel player) {
        AddNewPlayerReturnValue addNewPlayerReturnValue = new AddNewPlayerReturnValue(acounts.contains(player));
        if (!acounts.contains(player)) {
            acounts.add(player);
        }
        return addNewPlayerReturnValue;
    }

    public AddNewProgressReturnValue addNewProgress(ProgressModel progress) {
        AddNewProgressReturnValue addNewProgress = new AddNewProgressReturnValue();
        addNewProgress.isProgressNew(notFinishedProgresses.contains(progress));
        for (AccountModel player : progress.getPlayers()) {
            if (!acounts.contains(player)) {
                addNewProgress.arePlayersNew(false);
                break;
            }
        }
        addNewProgress.arePlayersNew(true);
        if (addNewProgress.isRequestValid()) {
            notFinishedProgresses.add(progress);
        }
        return addNewProgress;
    }

    //TODO not belong to Model
    public ArrayList<ProgressModel> getProgresses() {
        return notFinishedProgresses;
    }

    public ArrayList<AccountModel> getAcounts() {
        return acounts;
    }

    private ProgressModel findProgressByName(String progressName) {
        for (ProgressModel progress : notFinishedProgresses) {
            if (progress.equals(progressName)) {
                return progress;
            }
        }
        return null;
    }

    public LoadProgressReturnValue loadProgress(String progressName) {
        LoadProgressReturnValue loadProgressReturnValue =
                new LoadProgressReturnValue(findProgressByName(progressName) != null, isAnyProgressRunning());
        runningProgress = findProgressByName(progressName);
        return loadProgressReturnValue;
    }

    public UndoReturnValue

    private boolean isAnyProgressRunning() {
        if (runningProgress == null) {
            return false;
        }
        return true;
    }

    public boolean isProgressFinished() {
        return runningProgress.getCurTurnGame().isGameFinished();
    }

    public void endProgress() {
        notFinishedProgresses.remove(runningProgress);
        quitProgress();
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

    public void isProgressNew(boolean hasProgress) {
        this.hasProgress = hasProgress;
    }

    public boolean hasPlayer() {
        return hasPlayer;
    }

    public void arePlayersNew(boolean hasPlayer) {
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

class UndoReturnValue {
    private boolean canUndo = true;

    public void canNotUndo() {
        canUndo = false;
    }

    public boolean canUndo() {
        return canUndo;
    }
}