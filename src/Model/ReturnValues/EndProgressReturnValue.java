package Model.ReturnValues;

public class EndProgressReturnValue {
    private String winnerName;

    public EndProgressReturnValue(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getWinnerName() {
        return winnerName;
    }
}
