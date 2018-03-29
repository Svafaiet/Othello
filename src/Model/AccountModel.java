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

    //TODO not finished Games are Counted in this
    public void win() {
        totalWins += 1;
    }

    public void endedAGame() {
        totalGames += 1;
    }

    public boolean equals(String name) {
        return this.name.equals(name);
    }

    public String getAccountName() {
        return name;
    }

    //TODO not belong to Model
    public String showInformation() {
        return (name + " " + totalGames + " " + totalWins);
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
