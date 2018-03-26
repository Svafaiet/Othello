package Model;

public class PlayerModel {
    private String name;
    private int totalGames;
    private int totalWins;

    PlayerModel (String name) {
        this.name = name;
        totalGames = 0;
        //TODO maybe this needs to be 0
        totalWins = 0;
    }

    public void win() {
        totalWins += 1;
        totalGames += 1;
    }
    public void lose() {
        totalGames += 1;
    }

    public boolean equals(String name) {
        return this.name.equals(name);
    }

    public String toString() {
        return name;
    }

    public String showInformation(){
        return name + totalGames + totalWins;
    }
}
