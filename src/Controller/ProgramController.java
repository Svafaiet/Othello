package Controller;

import Model.*;
import View.*;
import View.UserRequestType;
import View.Viewables.Viewable;

public class ProgramController {
    ProgramView programView = new ProgramView();
    ProgramModel programModel = ProgramModel.getInstance();
    private UserRequestType userRequestType;
    private String requestText;
    private String[] requestWords;
    private InputChecker inputChecker = new InputChecker();
    private boolean waitingOnLoading = false;
    Viewable viewable;

    public UserRequestType getUserRequestType() {
        return userRequestType;
    }

    public String getRequestText() {
        return requestText;
    }

    public InputChecker getInputChecker() {
        return inputChecker;
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
                if(!programModel.undo().isUndoValid()) {
                    programView.showProgramRequest(new Viewable(ProgramRequestType.SHOW_INVALID_UNDO));
                }
                programView.showProgramRequest(new Viewable(ProgramRequestType.SHOW_OTHELLO_GAME)); //fixMe
                break;
            case NEW_GAME:
                if(!programModel.addNewProgress(requestWords[2], requestText.substring(9).split("\\s+")).isRequestValid()) {
                    programView.showProgramRequest(new Viewable(ProgramRequestType.SHOW_INVALID_NAME));
                } else {
                    programView.showProgramRequest(new Viewable(ProgramRequestType.SHOW_OTHELLO_GAME));//fixME
                }
                break;
            case LOAD_GAME:
                waitingOnLoading = true;
                break;
            case NEW_PLAYER:
                if(programModel.addNewAccount(requestWords[2]).hasPlayer()) {
                    programView.showProgramRequest(new Viewa);
                }
        }
    }

    private void quit() {
        if (programModel.isAnyProgressRunning()) {
            programModel.endProgress();
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
        } else if (requestText.matches(InputRegex.NEW_PLAYER) && !whichPartAreWeIn.isInMenu()) { //TODO maybe needs change
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
        return !isInGame() && isInLoading();
    }
}