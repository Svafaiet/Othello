package Model;

import java.util.ArrayList;

public class Othello {
    private Othello(){
        games = new ArrayList<>();
        runningGame = null;
    }

    public static Othello getInstance() {
        return new Othello();
    }

    private ArrayList<Game> games;

    public Game findGameByName (String gameName) {
        for (Game game : games) {
            if (game.equals(gameName)) {
                return game;
            }
        }
        return null;
    }

    private Game runningGame;

}
