package Model;

import java.util.ArrayList;

public class OthelloModel {
    private OthelloModel() {
        games = new ArrayList<>();
        runningGame = null;
    }

    public static OthelloModel getInstance() {
        return new OthelloModel();
    }

    private ArrayList<GameModel> games;
    private ArrayList<PlayerModel> players;

    public AddNewPlayerReturnValue addNewPlayer(PlayerModel player) {
        AddNewPlayerReturnValue addNewPlayerReturnValue = new AddNewPlayerReturnValue(players.contains(player));
        if (!players.contains(player)) {
            players.add(player);
        }
        return addNewPlayerReturnValue;
    }

    public AddNewGameReturnValue addNewGame(GameModel game) {
        AddNewGameReturnValue addNewGameReturnValue = new AddNewGameReturnValue();
        addNewGameReturnValue.setGameStatus(games.contains(game));
        addNewGameReturnValue.setPlayerStatus(players.contains(game.getPlayer1()) && players.contains(game.getPlayer2()));
        if (addNewGameReturnValue.isRequestValid()) {
            games.add(game);
            players.add(game.getPlayer1());
            players.add(game.getPlayer2());
        }
        return addNewGameReturnValue;
    }

    public EndGameReturnValue endGame(GameModel game) {
        EndGameReturnValue endGameReturnValue = new EndGameReturnValue(games.contains(game) || runningGame.equals(game));
        if (games.contains(game)) {
            games.remove(game);
        }
        if (game.whoWon() == PlayerType.PLAYER1) {
            //TODO add me
            game.getPlayer1().win();
            game.getPlayer2().lose();
        } else {
            game.getPlayer2().win();
            game.getPlayer1().lose();
        }
        return endGameReturnValue;
    }

    public GameModel findGameByName(String gameName) {
        for (GameModel game : games) {
            if (game.equals(gameName)) {
                return game;
            }
        }
        return null;
    }

    private GameModel runningGame;

    public boolean isAnyGameRunning() {
        if (runningGame == null) {
            return false;
        }
        return true;
    }

    public void quitGame() {
        runningGame = null;
    }

}

class AddNewGameReturnValue {

    private boolean hasGame;
    private boolean hasPlayer;

    public boolean hasGame() {
        return hasGame;
    }

    public void setGameStatus(boolean hasGame) {
        this.hasGame = hasGame;
    }

    public boolean hasPlayer() {
        return hasPlayer;
    }

    public void setPlayerStatus(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }

    public boolean isRequestValid() {
        return !hasGame && hasPlayer;
    }
}

class EndGameReturnValue {
    private boolean hasGame;

    public boolean hasGame() {
        return hasGame;
    }

    EndGameReturnValue(boolean hasGame) {
        this.hasGame = hasGame;
    }
}

class AddNewPlayerReturnValue {
    private boolean hasPlayer;

    public boolean hasPlayer() {
        return hasPlayer;
    }

    AddNewPlayerReturnValue(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }
}