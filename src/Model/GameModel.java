package Model;

public abstract class GameModel {
    //protected PlayerType Turn;
    public abstract GameModel clone();

    public abstract boolean isGameFinished();

    public abstract PlayerType whoWon();

    public abstract boolean isMoveValid(MoveModel move);

    public abstract void makeMove(MoveModel move);
    //TODO add me
}
