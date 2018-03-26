package Model.OthelloGameLogic;

public class Pair {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Pair sumOfPairs(Pair p1, Pair p2) {
        return new Pair((p1.x + p2.x), (p1.y + p2.y));
    }
}

