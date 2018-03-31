import Controller.ProgramController;
import Model.AccountModel;
import Model.ProgramModel;
import View.UserRequestType;

public class Program {
    public static void main(String[] args) {
        ProgramController programController = new ProgramController();
        do {
            programController.takeRequest();
            programController.handleRequests();
        } while (!programController.getUserRequestType().equals(UserRequestType.END));
    }
}
