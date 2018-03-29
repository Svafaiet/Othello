package Controller;

import Model.ProgramModel;
import View.ProgramView;

public class ProgramController {
    ProgramView programView = new ProgramView();
    ProgramModel programModel = ProgramModel.getInstance();


}

class InputChecker {

    public void distinguishRequest (String[] request) {
        switch (request) {
            case 
        }
    }
}

enum UserRequestType {
    NEW_PLAYER, NEW_GAME, PRINT_PLAYERS, LOAD_GAME, PUT_TAW_IN_OTHELLO, QUIT, END,
    INVALID_COMMAND
}