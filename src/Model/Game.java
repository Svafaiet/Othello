package Model;

public class Game {
    private String gameName;
    private Player player1;
    private Player player2;
    private Turn turn;
    private boolean hasUndone;
    private Game lastTurnGame;

    public boolean equals(String gameName) {
        return this.gameName.equals(gameName);
    }
}

enum Turn {
    PLAYER1, PLAYER2
}