package Model;

public class AccountModel implements Comparable<AccountModel> {
    private String name;
    private int totalGames;
    private int totalWins;

    AccountModel(String name) {
        this.name = name;
        totalGames = 0;
        //TODO maybe this needs to be 0
        totalWins = 0;
    }

    //Alert not finished Games are Counted in this
    public void win() {
        totalWins += 1;
    }

    public void endedAGame() {
        totalGames += 1;
    }

    public boolean equals(String name) {
        return this.name.equals(name);
    }

    @Override
    public boolean equals(Object obj) {
        AccountModel accountModel = (AccountModel) obj;
        return equals(accountModel.getAccountName());
    }

    public String getAccountName() {
        return name;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public int getTotalWins() {
        return totalWins;
    }

    @Override
    public int compareTo(AccountModel anotherAccount) {
        if (anotherAccount.totalWins > totalWins) {
            return -1;
        } else if (anotherAccount.totalWins < totalWins) {
            return 1;
        } else {
            if (anotherAccount.totalGames > totalGames) {
                return 1;
            } else if (anotherAccount.totalGames < totalGames) {
                return -1;
            } else {
                return this.name.compareTo(anotherAccount.name);
            }
        }
    }

}
