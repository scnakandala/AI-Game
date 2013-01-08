package gameengine;

import ai.AIPlayer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.GameMap;

public class Main {

    public static void main(String[] args) {
        final GameEngine gameEngine = GameEngine.getInstance();
        AIPlayer aIPlayer = new AIPlayer();
        gameEngine.addObserver(aIPlayer);
        (new Runnable() {
            public void run() {
                GameMap map;
                try {
                    map = new GameMap();
                    gameEngine.addObserver(map);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).run();
        gameEngine.join();
    }
}
