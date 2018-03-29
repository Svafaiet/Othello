package Model;

public enum PlayerType {
    PLAYER1, PLAYER2, NO_ONE;

    public int toIndex () {
        switch (this) {
            case PLAYER1:
                return 0;
            case PLAYER2:
                return 1;
            case NO_ONE:
                return Integer.MAX_VALUE;
        }
        return Integer.MAX_VALUE;
    }

}
