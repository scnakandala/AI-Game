/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import AIClient.AIAgent;
import Connectors.Reader;
import MessageParser.MessageParser;
import Models.Board;

/**
 *
 * @author naka
 */
public class Main {
    public static void main(String[] args) {
       Board board = new Board();
       MessageParser parser = new MessageParser(board);
       Reader reader = new Reader(parser);
       parser.joinGame();
       AIAgent aIClient = new AIAgent(parser,board);
       parser.addObserver(board);
       parser.addObserver(aIClient);
       (new Thread(reader)).start();
       (new Thread(aIClient)).start();
    }
}
