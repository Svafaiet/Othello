package View;

import View.Viewables.Viewable;

import java.util.Scanner;

public class ProgramView {
    private Scanner scanner = new Scanner(System.in);
    private ProgressView progressView = new ProgressView();
    private AccountView accountView = new AccountView();

    public String getUserRequest() {
        String requestText;
        requestText = scanner.nextLine().replaceAll("\\s+", " ");
        requestText = requestText.replaceAll("^ ", "").replaceAll(" $", "");
        System.out.println(requestText);
        return requestText;
    }

    public void showProgramRequest(Viewable viewable) {
        switch (viewable.getRequestType()) {
            case SHOW_WINNER:
                progressView.showWiningPlayer(viewable);
                break;
            case SHOW_ACCOUNT:
                accountView.showAccount(viewable);
                break;
            case SHOW_INVALID_NAME:
                invalidName();
                break;
            case SHOW_INVALID_UNDO:
                invalidUndo();
                break;
            case SHOW_DUPLICATE_NAME:
                duplicatAccountName(viewable);
                break;
            case SHOW_INVALID_CHOICE:
                invalidChoice();
                break;
            case SHOW_INVALID_COMMAND:
                invalidCommand();
                break;
            case SHOW_OTHELLO_GAME:
                progressView.showGame(viewable);
                break;
            case SHOW_PROGRESS:
                progressView.showProgress(viewable);
                break;
        }
    }

    public void invalidUndo() {
        System.out.println(Massages.INVALID_UNDO);
    }

    public void invalidName() {
        System.out.println(Massages.INVALID_NAME);
    }

    public void invalidCommand() {
        System.out.println(Massages.INVALID_COMMAND);
    }

    public void duplicatAccountName(Viewable viewable) {
        System.out.println(Massages.DUPlICATE_ACCOUNT_MASSAGE
                .replace("player_name", accountView.getAccountName(viewable)));
    }

    public void invalidChoice() {
        System.out.println(Massages.INVALID_CHOICE);
    }

}

class Massages {
    public static final String INVALID_CHOICE = "invalid choice";
    public static final String INVALID_UNDO = "invalid undo";
    public static final String INVALID_NAME = "invalid name";
    public static final String INVALID_COMMAND = "invalid command";
    public static final String DUPlICATE_ACCOUNT_MASSAGE = "name player_name is already used";
}