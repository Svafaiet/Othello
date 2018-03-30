package Controller;

import Model.*;
import Model.ReturnValues.EndProgressReturnValue;
import View.*;
import View.OthelloView.Viewables.OthelloViewable;
import View.UserRequestType;
import View.Viewables.AccountViewable;
import View.Viewables.PlayerViewable;
import View.Viewables.ProgressViewable;
import View.Viewables.Viewable;

import java.util.Collection;
import java.util.Collections;

public class ProgramController {
    private ProgramView programView = new ProgramView();
    private ProgramModel programModel = ProgramModel.getInstance();
    private UserRequestType userRequestType;
    private String requestText;
    private String[] requestWords;
    private InputChecker inputChecker = new InputChecker();
    private boolean waitingOnLoading = false;

    public UserRequestType getUserRequestType() {
        return userRequestType;
    }

    public void takeRequest() {
        requestText = programView.getUserRequest();
        requestWords = requestText.split("\\s+");
        inputChecker.distinguishRequest(requestText, new WhichPartAreWeIn(programModel.isAnyProgressRunning(), waitingOnLoading));
        userRequestType = inputChecker.getUserRequestType();
    }

    public void handleRequests() {
        switch (userRequestType) {
            case END:
                return;
            case QUIT:
                quit();
                break;
            case UNDO:
                undo();
                break;
            case NEW_GAME:
                newGame();
                break;
            case LOAD_GAME:
                loadGame();
                break;
            case NEW_PLAYER:
                newPlayer();
                break;
            case PRINT_PLAYERS:
                printPlayers();
                break;
            case LOAD_GAME_NAME:
                loadGameName();
                break;
            case PUT_TAW_IN_OTHELLO:
                putTawInOthello();
                break;
            case INVALID_COMMAND:
                invalidCommand();
                break;
        }
    }

    private void invalidCommand() {
        programView.showProgramRequest(new Viewable(ProgramRequestType.SHOW_INVALID_COMMAND));
    }

    private void putTawInOthello() {
        if (!programModel.makeMove(new ProgressController(programModel.getRunningProgress()).makeMoveForGame(requestText)).isMoveValid()) {
            programView.showProgramRequest(new Viewable(ProgramRequestType.SHOW_INVALID_CHOICE));
        } else {
            showOthelloGame();
            if (programModel.isProgressFinished()) {
                EndProgressReturnValue endProgressReturnValue = programModel.endProgress();
                programView.showProgramRequest(
                        new PlayerController(endProgressReturnValue.getWinnerName()).makeShowWinnerViewable());
            }
        }
    }

    private void showOthelloGame() {
        programView.showProgramRequest(
                new ProgressController(programModel.getRunningProgress()).makeShowOthelloGameViewable());
    }

    private void loadGameName() {
        if (!programModel.loadProgress(requestText).hasGame()) {
            programView.showProgramRequest(new Viewable(ProgramRequestType.SHOW_INVALID_NAME));
        } else {
            waitingOnLoading = false;
            showOthelloGame();
        }
    }

    private void printPlayers() {
        Collections.sort(programModel.getAccounts());
        for (AccountModel account : programModel.getAccounts()) {
            programView.showProgramRequest(new AccountController(account).makeShowAccount());
        }
    }

    private void newPlayer() {
        if (programModel.addNewAccount(requestWords[2]).hasPlayer()) {
            programView.showProgramRequest(new AccountController(requestWords[2]).makeDuplicatAccountViewable());
        }
    }

    private void loadGame() {
        waitingOnLoading = true;
        for (ProgressModel progress : programModel.getProgresses()) {
            programView.showProgramRequest(new ProgressController(progress).makeShowProgressViewable());
        }
    }

    private void newGame() {
        if (!programModel.addNewProgress(requestWords[requestWords.length - 1],
                requestText.substring(9, requestText.length() - requestWords[requestWords.length - 1].length() - 1).split("\\s+")).isRequestValid()) {
            programView.showProgramRequest(new Viewable(ProgramRequestType.SHOW_INVALID_NAME));
        } else {
            showOthelloGame();
        }
    }

    private void undo() {
        if (!programModel.undo().isUndoValid()) {
            programView.showProgramRequest(new Viewable(ProgramRequestType.SHOW_INVALID_UNDO));
        }
        showOthelloGame();
    }

    private void quit() {
        if (programModel.isAnyProgressRunning()) {
            programModel.quitProgress();
            return;
        }
        waitingOnLoading = false;
    }


}

class InputChecker {
    private UserRequestType userRequestType;

    public UserRequestType getUserRequestType() {
        return userRequestType;
    }

    public void distinguishRequest(String requestText, WhichPartAreWeIn whichPartAreWeIn) {
        if (requestText.equals("")) {
            userRequestType = UserRequestType.NOTHING;
        } else if (requestText.matches(InputRegex.END)) {
            userRequestType = UserRequestType.END;
        } else if (requestText.matches(InputRegex.QUIT) && !whichPartAreWeIn.isInMenu()) {
            userRequestType = UserRequestType.QUIT;
        } else if (requestText.matches(InputRegex.LOAD_GAME_NAME) && whichPartAreWeIn.isInLoading()) {
            userRequestType = UserRequestType.LOAD_GAME_NAME;
        } else if (requestText.matches(InputRegex.UNDO) && whichPartAreWeIn.isInGame()) {
            userRequestType = UserRequestType.UNDO;
        } else if (requestText.matches(InputRegex.NEW_PLAYER) && whichPartAreWeIn.isInMenu()) { //TODO maybe needs change
            userRequestType = UserRequestType.NEW_PLAYER;
        } else if (requestText.matches(InputRegex.NEW_GAME) && whichPartAreWeIn.isInMenu()) {
            userRequestType = UserRequestType.NEW_GAME;
        } else if (requestText.matches(InputRegex.PRINT_PLAYERS) && whichPartAreWeIn.isInMenu()) {
            userRequestType = UserRequestType.PRINT_PLAYERS;
        } else if (requestText.matches(InputRegex.LOAD_GAME) && whichPartAreWeIn.isInMenu()) {
            userRequestType = UserRequestType.LOAD_GAME;
        } else if (requestText.matches(InputRegex.PUT_TAW_IN_OTHELLO) && whichPartAreWeIn.isInGame()) {
            userRequestType = UserRequestType.PUT_TAW_IN_OTHELLO;
        } else {
            userRequestType = UserRequestType.INVALID_COMMAND;
        }
    }
}

class InputRegex {
    public static final String NEW_PLAYER = "^(\\s*new player \\S+\\s*)$";
    public static final String NEW_GAME = "^(\\s*new game \\S+ \\S+ \\S+\\s*)$";
    public static final String PRINT_PLAYERS = "^(\\s*print players\\s*)$";
    public static final String LOAD_GAME = "^(\\s*load game\\s*)$";
    public static final String LOAD_GAME_NAME = "^(\\s*\\S+\\s*)$";
    public static final String UNDO = "^(\\s*undo\\s*)$";
    public static final String QUIT = "^(\\s*quit\\s*)$";
    public static final String END = "^(\\s*end\\s*)$";
    public static final String PUT_TAW_IN_OTHELLO = "^(\\s*[A-F][1-6]\\s*)$";
}

class WhichPartAreWeIn {
    private boolean inGame;
    private boolean inLoading;

    public WhichPartAreWeIn(boolean inGame, boolean inLoading) {
        this.inGame = inGame;
        this.inLoading = inLoading;
    }

    public boolean isInGame() {
        return inGame;
    }

    public boolean isInLoading() {
        return inLoading;
    }

    public boolean isInMenu() {
        return !isInGame() && !isInLoading();
    }
}