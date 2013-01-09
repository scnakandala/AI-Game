package gameengine;

import ai.AIPlayerVersion1;
import ui.Settings;

public class Main {

    public static void main(String[] args) {
        final GameEngine gameEngine = GameEngine.getInstance();
        AIPlayerVersion1 aIPlayer = new AIPlayerVersion1();
        gameEngine.addObserver(aIPlayer);
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
