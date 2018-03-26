package Model;

public class GameModel {
    private String gameName;
    private PlayerModel player1;
    private PlayerModel player2;
    private PlayerType turn;
    private boolean hasUndone;
    private GameModel lastTurnGame;

    public PlayerModel getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerModel player1) {
        this.player1 = player1;
    }

    public PlayerModel getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerModel player2) {
        this.player2 = player2;
    }

    public boolean equals(String gameName) {
        return this.gameName.equals(gameName);
    }

    public String toString(){
        return gameName + " " + player1 + " " + player2;
        //TODO maybe a change is needed
    }
}
