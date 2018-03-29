package View;

import View.Viewables.AccountViewable;
import View.Viewables.Viewable;

public class AccountView {

    public void showAccount(Viewable viewable) {
        AccountViewable accountViewable = (AccountViewable) viewable;
        System.out.println(accountViewable.getName() + " " + accountViewable.getWinCount() + " " + accountViewable.getTotalGames());
    }

    public String getAccountName(Viewable viewable) {
        AccountViewable accountViewable = (AccountViewable) viewable;
        return accountViewable.getName();
    }
}
