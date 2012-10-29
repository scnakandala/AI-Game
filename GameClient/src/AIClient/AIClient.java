/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AIClient;

import MessageParser.MessageParser;
import Models.Board;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author naka
 */
public class AIClient implements Observer, Runnable {

    private boolean makeMove = false;
    private int move = -1;
    private MessageParser messageParser;
    private int[][] b;
    private boolean[][] visited;
    private Board board;

    public AIClient(MessageParser parser,Board aBoard) {
        messageParser = parser;
        board = aBoard;
    }

    @Override
    public void run() {
        while (true) {
            if (makeMove) {
                switch (move) {
                    case 0:
                        messageParser.moveUpwards();
                        break;
                    case 1:
                        messageParser.moveLeft();
                        break;
                    case 2:
                        messageParser.moveDownwards();
                        break;
                    case 3:
                        messageParser.moveLeft();
                        break;
                }
                makeMove = false;
                move = (move +1)%4;
            }
        }
    }

    public void update(Observable o, Object arg) {
        if (o instanceof MessageParser) {
            if (arg instanceof String) {
                arg = (String) arg;
                if (arg.equals("GLOBAL_UPDATE")) {
                    makeMove = true;
                }
            }
        }
    }

    public void calculateBestMove(){
        b = board.getBoard();
        visited = new boolean[Board.size][Board.size];
    }
}
