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
    private ArrayList<AccountModel> accounts;
    private ProgressModel runningProgress;

    public ArrayList<ProgressModel> getProgresses() {
        return notFinishedProgresses;
    }

    public ArrayList<AccountModel> getAccounts() {
        return accounts;
    }

    private AccountModel findAccountByName(String accountName) {
        for(AccountModel account : accounts) {
            if(account.equals(accountName)) {
                return account;
            }
        }
        return null;
    }

    private ProgressModel findProgressByName(String progressName) {
        for (ProgressModel progress : notFinishedProgresses) {
            if (progress.equals(progressName)) {
                return progress;
            }
        }
        return null;
    }

    public AddNewPlayerReturnValue addNewAccount(AccountModel account) {
        AddNewPlayerReturnValue addNewPlayerReturnValue = new AddNewPlayerReturnValue(accounts.contains(account));
        if (!accounts.contains(account)) {
            accounts.add(account);
        }
        return addNewPlayerReturnValue;
    }

    public AddNewProgressReturnValue addNewProgress(String progressName, String[] players) {
        AddNewProgressReturnValue addNewProgress = new AddNewProgressReturnValue();
        addNewProgress.isProgressNew(findProgressByName(progressName) != null);
        for (String playerName : players) {
            if (findAccountByName(playerName) != null) {
                addNewProgress.arePlayersNew(true);
                break;
            }
        }
        addNewProgress.arePlayersNew(false);
        if (addNewProgress.isRequestValid()) {
            ProgressModel progress = ProgressModel.makeProgress(progressName, players);
            notFinishedProgresses.add(progress);
        }
        return addNewProgress;
    }

    public LoadProgressReturnValue loadProgress(String progressName) {
        LoadProgressReturnValue loadProgressReturnValue =
                new LoadProgressReturnValue(findProgressByName(progressName) != null, isAnyProgressRunning());
        runningProgress = findProgressByName(progressName);
        return loadProgressReturnValue;
    }

    public UndoReturnValue undo(String progressName) {
        ProgressModel progress = findProgressByName(progressName);
        if (progress.isUndoValid()) {
            progress.useUndo();
            return new UndoReturnValue();
        }
        UndoReturnValue undoReturnValue = new UndoReturnValue();
        undoReturnValue.canNotUndo();
        return undoReturnValue;
    }

    public MakeMoveReturnValue makeMove(MoveModel move) {
        return runningProgress.makeMove(move);
    }

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
        if(runningProgress.getCurTurnGame().whoWonIndex() == Integer.MAX_VALUE) {
            return;
        }

        for(PlayerModel player : runningProgress.getPlayers()) {
            findAccountByName(player.getPlayerName()).endedAGame();
        }
        String winnerName = runningProgress.getPlayers().get(
                runningProgress.getCurTurnGame().whoWonIndex())
                .getPlayerName();
        findAccountByName(winnerName).win();
        quitProgress();
    }

    private void quitProgress() {
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