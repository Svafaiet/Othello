package Model.OthelloGameLogic;

import Model.*;

public class OthelloModel extends GameModel {

    public final int RANGE = 6;
    private final int MIDDLE_MINUS = (RANGE - 1) / 2;
    private final int MIDDLE_PLUS = (RANGE + 1) / 2;

    private boolean isInRange(int x) {
        return (x >= 0) && (x < RANGE);
    }

    private boolean isInRange(Pair pair) {
        return isInRange(pair.getX()) && isInRange(pair.getY());
    }

    private Cell[][] cells;
    private PlayerType playerTurn = PlayerType.PLAYER1;
    private boolean isGameFinished = false;

    public OthelloModel() {
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
        for (int i = 0; i < RANGE; i++) {
            System.arraycopy(cells[i], 0, newOthelloModel.cells[i], 0, RANGE);
        }
        newOthelloModel.playerTurn = playerTurn;
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

    private boolean isCellOpposite(Pair point) {
        if (!isInRange(point)) {
            return false;
        }
        return (at(point) == Cell.oppositeCellType(playerTurn));
    }

    private boolean isCellSame(Pair point) {
        if (!isInRange(point)) {
            return false;
        }
        return (at(point) == Cell.cellType(playerTurn));
    }

    private void passTurn() {
        switch (playerTurn) {
            case NO_ONE:
                playerTurn = PlayerType.NO_ONE;
                break;
            case PLAYER2:
                playerTurn = PlayerType.PLAYER1;
                break;
            case PLAYER1:
                playerTurn = PlayerType.PLAYER2;
                break;
        }
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
            if (isCellOpposite(Direction.moveInDirection(dir, point, 1))) {
                for (int i = 2; i < 6; i++) {
                    if (isCellSame(Direction.moveInDirection(dir, point, i))) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    private boolean canPlayerMove() {
        for (int i = 0; i < RANGE; i++) {
            for (int j = 0; j < RANGE; j++) {
                if (isMoveValid(new OthelloMoveModel(i, j))) {
                    return true;
                }
            }
        }
        return false;
    }

    public int whoseTurnItIs() {
        return playerTurn.toIndex();
    }

    private void reverseTaw(Pair point) {
        setTaw(point, Cell.oppositeCellType(at(point)));
    }

    private void reverseTawsInDirection(Pair point, Pair dir, OthelloMoveModel othelloMove) {
        int distance = 1;
        while (isInRange(point) &&
                (at(Direction.moveInDirection(dir, point, distance)) ==
                        Cell.oppositeCellType(playerTurn))) {
            reverseTaw(Direction.moveInDirection(dir, point, distance));
            distance++;
        }
    }

    private void reverseTawsInDirections(Pair point, OthelloMoveModel othelloMove) {
        for (Pair dir : Direction.ALL_DIRECTIONS) {
            reverseTawsInDirection(point, dir, othelloMove);
        }
    }

    public void putTawInPoint(MoveModel move) {
        OthelloMoveModel othelloMove = (OthelloMoveModel) move;
        Pair point = new Pair(othelloMove.getX(), othelloMove.getY());
        setTaw(point, Cell.cellType(playerTurn));
        reverseTawsInDirections(point, othelloMove);
        passTurn();
        if (!canPlayerMove()) {
            passTurn();
            if (!canPlayerMove()) {
                isGameFinished = true;
            }
        }

    }

    @Override
    public void makeMove(MoveModel move) {
        putTawInPoint(move);
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public int whoWonIndex() {
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
                return PlayerType.PLAYER1.toIndex();
            }
            if (player1Count < player2Count) {
                return PlayerType.PLAYER2.toIndex();
            }
            return PlayerType.NO_ONE.toIndex();
        } else {
            return PlayerType.NO_ONE.toIndex();
        }
    }

}