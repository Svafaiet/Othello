package Model;

import Model.ReturnValues.*;

import java.util.ArrayList;

public class ProgramModel {
    private ProgramModel() {
        notFinishedProgresses = new ArrayList<>();
        accounts = new ArrayList<>();
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

    public ProgressModel getRunningProgress() {
        return runningProgress;
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

    public AddNewPlayerReturnValue addNewAccount(String accountName) {
        AccountModel account = new AccountModel(accountName);
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
            if (findAccountByName(playerName) == null) {
                addNewProgress.arePlayersNew(false);
                break;
            }
        }
        addNewProgress.arePlayersNew(true);
        if (addNewProgress.isRequestValid()) {
            ProgressModel progress = ProgressModel.makeProgress(progressName, players);
            notFinishedProgresses.add(progress);
            runningProgress = progress;
        }
        return addNewProgress;
    }

    public LoadProgressReturnValue loadProgress(String progressName) {
        LoadProgressReturnValue loadProgressReturnValue =
                new LoadProgressReturnValue(findProgressByName(progressName) != null, isAnyProgressRunning());
        runningProgress = findProgressByName(progressName);
        return loadProgressReturnValue;
    }

    public UndoReturnValue undo() {
        String progressName = runningProgress.getProgressName();
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

    public boolean isAnyProgressRunning() {
        if (runningProgress == null) {
            return false;
        }
        return true;
    }

    public boolean isProgressFinished() {
        return runningProgress.getCurTurnGame().isGameFinished();
    }

    public EndProgressReturnValue endProgress() {
        notFinishedProgresses.remove(runningProgress);
        if(runningProgress.getCurTurnGame().whoWonIndex() == Integer.MAX_VALUE) {
            return new EndProgressReturnValue("No One");
        }

        for(PlayerModel player : runningProgress.getPlayers()) {
            findAccountByName(player.getPlayerName()).endedAGame();
        }
        PlayerModel winner = runningProgress.whoseTurnItIs();
        findAccountByName(winner.getPlayerName()).win();
        quitProgress();
        return new EndProgressReturnValue(winner.getPlayerName());
    }

    public void quitProgress() {
        runningProgress = null;
    }

}

