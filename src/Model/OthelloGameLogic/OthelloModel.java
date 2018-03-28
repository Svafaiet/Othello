package Model.OthelloGameLogic;

import Model.*;

public class OthelloModel extends GameModel {

    private static final int RANGE = 6;
    private static final int MIDDLE_MINUS = (RANGE - 1) / 2;
    private static final int MIDDLE_PLUS = (RANGE + 1) / 2;

    private boolean isInRange(int x) {
        return (x >= 0) && (x < RANGE);
    }

    private boolean isInRange(Pair pair) {
        return isInRange(pair.getX()) && isInRange(pair.getY());
    }

    private Cell[][] cells;

    OthelloModel() {
        cells = new Cell[RANGE][RANGE];
        for (int i = 0; i < RANGE; i++) {
            for (int j = 0; j < RANGE; j++) {
                cells[i][j] = Cell.CELL_EMPTY;
            }
        }
        cells[MIDDLE_MINUS][MIDDLE_MINUS] = Cell.CELL_PLAYER1;
        cells[MIDDLE_PLUS][MIDDLE_PLUS] = Cell.CELL_PLAYER1;
        cells[MIDDLE_MINUS][MIDDLE_PLUS] = Cell.CELL_PLAYER2;
        cells[MIDDLE_PLUS][MIDDLE_MINUS] = Cell.CELL_PLAYER2;
    }

    @Override
    public OthelloModel clone() {
        OthelloModel newOthelloModel = new OthelloModel();
        for (int i = 0; i < 6; i++) {
            System.arraycopy(cells[i], 0, newOthelloModel.cells[i], 0, 6);
        }
        return newOthelloModel;
    }

    public Cell at(int i, int j) {
        return cells[i][j];
    }

    public Cell at(Pair pair) {
        return cells[pair.getX()][pair.getY()];
    }

    private boolean setTaw(int i, int j, Cell cell) {
        if (isInRange(i) && isInRange(j)) {
            cells[i][j] = cell;
            return true;
        } else {
            return false;
        }
    }

    private boolean setTaw(Pair point, Cell cell) {
        return setTaw(point.getX(), point.getY(), cell);
    }

    private boolean isCellOpposite(Pair point, OthelloMoveModel othelloMove) {
        if (!isInRange(point)) {
            return false;
        }
        return (at(point) == CellFunctions.oppositeCellType(othelloMove.getPlayerType()));
    }

    private boolean isCellSame(Pair point, OthelloMoveModel othelloMove) {
        if (!isInRange(point)) {
            return false;
        }
        return (at(point) == CellFunctions.cellType(othelloMove.getPlayerType()));
    }

    public boolean isMoveValid(MoveModel move) {
        OthelloMoveModel othelloMove = (OthelloMoveModel) move;
        //TODO make sure PlayerType is not NO_ONE
        Pair point = new Pair(othelloMove.getX(), othelloMove.getY());
        if (!isInRange(point)) {
            return false;
        }
        if (at(point) != Cell.CELL_EMPTY) {
            return false;
        }
        for (Pair dir : Direction.ALL_DIRECTIONS) {
            if (isCellOpposite(Direction.moveInDirection(dir, point, 1), othelloMove)) {
                for (int i = 2; i < 6; i++) {
                    if (isCellSame(Direction.moveInDirection(dir, point, i), othelloMove)) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    private void reverseTaw(Pair point) {
        setTaw(point, CellFunctions.oppositeCellType(at(point)));
    }

    private void reverseTawsInDirection(Pair point, Pair dir, OthelloMoveModel othelloMove) {
        int distance = 1;
        while (isInRange(point) &&
                (at(Direction.moveInDirection(dir, point, distance)) ==
                        CellFunctions.oppositeCellType(othelloMove.getPlayerType()))) {
            reverseTaw(Direction.moveInDirection(dir, point, distance));
            distance++;
        }
    }

    private void reverseTawsInDirections(Pair point, OthelloMoveModel othelloMove) {
        for (Pair dir : Direction.ALL_DIRECTIONS) {
            reverseTawsInDirection(point, dir, othelloMove);
        }
    }

    public PutTawReturnValue putTawInPoint(MoveModel move) {
        OthelloMoveModel othelloMove = (OthelloMoveModel) move;
        Pair point = new Pair(othelloMove.getX(), othelloMove.getY());
        if (isMoveValid(move)) {
            setTaw(point, CellFunctions.cellType(othelloMove.getPlayerType()));
            reverseTawsInDirections(point, othelloMove);
            return new PutTawReturnValue(true);
        } else {
            return new PutTawReturnValue(false);
        }
    }

    public boolean isGameFinished() {
        for (int i = 0; i < RANGE; i++) {
            for (int j = 0; j < RANGE; j++) {
                if (isMoveValid(new OthelloMoveModel(PlayerType.PLAYER1, i, j))) {
                    return false;
                }
                if (isMoveValid(new OthelloMoveModel(PlayerType.PLAYER2, i, j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public PlayerType whoWon() {
        if (isGameFinished()) {
            int player1Count = 0;
            int player2Count = 0;
            for (int i = 0; i < RANGE; i++) {
                for (int j = 0; j < RANGE; j++) {
                    if (at(i, j) == Cell.CELL_PLAYER1) {
                        player1Count++;
                    }
                    if (at(i, j) == Cell.CELL_PLAYER2) {
                        player2Count++;
                    }
                }
            }
            if (player1Count > player2Count) {
                return PlayerType.PLAYER1;
            }
            if (player1Count < player2Count) {
                return PlayerType.PLAYER2;
            }
            return PlayerType.NO_ONE;
        } else {
            return PlayerType.NO_ONE;
        }
    }


}

class PutTawReturnValue {
    private boolean isValid = true;

    PutTawReturnValue(boolean isValid) {
        this.isValid = isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isValid() {
        return isValid;
    }
}