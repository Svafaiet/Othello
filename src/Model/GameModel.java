package Model;

public abstract class GameModel {
    //protected PlayerType Turn;
    public abstract GameModel clone();
    public abstract PlayerType whoWon();
    public abstract boolean isGameFinished();
    //TODO add me
}
