package Controller;

import Model.AccountModel;
import View.ProgramRequestType;
import View.Viewables.AccountViewable;

public class AccountController {
    private AccountModel accountModel;
    private String accountName;

    public AccountController(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    public AccountController(String accountName) {
        this.accountName = accountName;
    }

    public AccountViewable makeShowAccount() {
        AccountViewable accountViewable = new AccountViewable(accountModel.getAccountName(), accountModel.getTotalWins(), accountModel.getTotalGames());
        accountViewable.setRequestType(ProgramRequestType.SHOW_ACCOUNT);
        return accountViewable;
    }

    public AccountViewable makeDuplicatAccountViewable() {
        AccountViewable accountViewable = new AccountViewable(accountName, 0, 0);
        accountViewable.setRequestType(ProgramRequestType.SHOW_DUPLICATE_NAME);
        return accountViewable;
    }
}
