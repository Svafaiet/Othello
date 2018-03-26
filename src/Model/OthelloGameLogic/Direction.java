package Model.OthelloGameLogic;

import java.util.ArrayList;

public class Direction {

    public final Pair DIR_UP = new Pair(0, -1);
    public final Pair DIR_DOWN = new Pair(0, 1);
    public final Pair DIR_LEFT = new Pair(-1, 0);
    public final Pair DIR_RIGHT = new Pair(1, 0);
    public final Pair DIR_UP_RIGHT = Pair.sumOfPairs(DIR_UP, DIR_RIGHT);
    public final Pair DIR_UP_LEFT = Pair.sumOfPairs(DIR_UP, DIR_LEFT);
    public final Pair DIR_DOWN_RIGHT = Pair.sumOfPairs(DIR_DOWN, DIR_RIGHT);
    public final Pair DIR_DOWN_LEFT = Pair.sumOfPairs(DIR_DOWN, DIR_LEFT);

    public static final ArrayList<Pair> ALL_DIRECTIONS = new ArrayList<>();

    {
        ALL_DIRECTIONS.add(DIR_UP);
        ALL_DIRECTIONS.add(DIR_DOWN);
        ALL_DIRECTIONS.add(DIR_RIGHT);
        ALL_DIRECTIONS.add(DIR_LEFT);
        ALL_DIRECTIONS.add(DIR_UP_RIGHT);
        ALL_DIRECTIONS.add(DIR_DOWN_LEFT);
        ALL_DIRECTIONS.add(DIR_DOWN_RIGHT);
        ALL_DIRECTIONS.add(DIR_UP_LEFT);
    }

    public static Pair moveInDirection(Pair dir, Pair point, int distance) {
        return new Pair(point.getX() + distance * dir.getX(), point.getY() + distance * dir.getY());
    }

}
