import Controller.ProgramController;
import Model.AccountModel;
import Model.ProgramModel;
import View.UserRequestType;

public class Program {
    public static void main(String[] args) {


        ProgramController programController = new ProgramController();
        do {
            ProgramModel pm = programController.getProgramModel();
            pm.getAccounts().add(new AccountModel("svt", 5, 1));
            pm.getAccounts().add(new AccountModel("svt2", 5, 1));
            pm.getAccounts().add(new AccountModel("svt3", 4, 3));
            pm.getAccounts().add(new AccountModel("zvt", 5, 5));
            programController.printPlayers();
            programController.takeRequest();
            programController.handleRequests();
        } while (!programController.getUserRequestType().equals(UserRequestType.END));
    }
}
