package gameengine;

import ai.AIPlayer;
import ui.Settings;

public class Main {

    public static void main(String[] args) {
        final GameEngine gameEngine = GameEngine.getInstance();
        // Creates an AI agent and adds it as an observer for the GameEngine
        AIPlayer aIPlayer = new AIPlayer();
        gameEngine.addObserver(aIPlayer);
        
        // Runs the Settings UI
        // This thread then creates the Main GUI thread and joins the game
        new Runnable() {
            public void run() {
                Settings dialog = new Settings(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        }.run();
    }
}
