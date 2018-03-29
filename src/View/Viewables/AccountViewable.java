package View.Viewables;

public class AccountViewable extends Viewable {
    private String name;
    private int winCount;
    private int totalGames;

    public AccountViewable(String name, int winCount, int totalGames) {
        this.name = name;
        this.winCount = winCount;
        this.totalGames = totalGames;
    }

    public String getName() {
        return name;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getTotalGames() {
        return totalGames;
    }
}
