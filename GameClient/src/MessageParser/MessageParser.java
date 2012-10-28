/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageParser;

import Connectors.Writer;
import Models.Board;
import java.util.Observable;

/**
 *
 * @author Supun Nakandala
 */
public class MessageParser extends Observable {

    public static final int size = 20;
    private Writer writer;
    private Board board;
    private String join = "JOIN#";
    private String moveUpwards = "UP#";
    private String moveDownwards = "DOWN#";
    private String moveRight = "RIGHT#";
    private String moveLeft = "LEFT#";
    private String shoot = "SHOOT#";
    private String playersFull = "PLAYERS_FULL";
    private String alreadyAdded = "ALREADY_ADDED";
    private String gameAlreadyStarted = "GAME_ALREADY_STARTED";
    private String gameOver = "GAME_FINISHED";

    public MessageParser(Board b) {
        writer = new Writer();
        board = b;
    }

    public boolean joinGame() {
        return writer.sendMessage(join);
    }

    public boolean moveUpwards() {
        return writer.sendMessage(moveUpwards);
    }

    public boolean moveDownwards() {
        return writer.sendMessage(moveDownwards);
    }

    public boolean moveRight() {
        return writer.sendMessage(moveRight);
    }

    public boolean moveLeft() {
        return writer.sendMessage(moveLeft);
    }

    public boolean shoot() {
        return writer.sendMessage(shoot);
    }

    public void setNewMessage(String message) {
        if (message.equals(playersFull)) {
            System.out.println("Players Full");
        } else if (message.equals(gameAlreadyStarted)) {
            System.out.println("Game Already Started");
        } else if (message.equals(alreadyAdded)) {
            System.out.println("Trying to join the game.But have already joined the game");
        } else if (message.startsWith("I")) {
            System.out.println("Player registered for the game");
            setBoardStructure(message);
        } else if (message.startsWith("S")) {
            System.out.println("Game Started");
            updateOtherPlayers(message);
        } else if (message.startsWith("G")) {
            System.out.println("Global update recieved");
            setGlobalUpdate(message);
        } else if (message.startsWith("C")) {
            System.out.println("Coin pile appeared");
        } else if (message.startsWith("L")) {
            System.out.println("Life pack appeared");
        } else if (message.equals(gameOver)) {
            System.out.println("Game finished");
        } else {
            System.out.println("Someother message appeared");
        }
    }

    private void updateOtherPlayers(String s) {
        String[] arr = s.split(":");
        int[][] b = board.getBoard();
        int thisPlayerNumber = board.getPlayerNumber();
        for (int i = 0; i < arr.length - 1; i++) {
            String[] player = arr[i + 1].split(";");
            int xPos = Integer.parseInt(player[1].split(",")[0]);
            int yPos = Integer.parseInt(player[1].split(",")[1]);
            int direction = Integer.parseInt(player[2].trim());
            int num = (i + 1) * 10 + direction;
            b[xPos][yPos] = num;
            if (thisPlayerNumber == i + 1) {
                board.setPlayerPositins(new int[]{xPos, yPos, direction});
            }
        }
        board.setBoard(b);
    }

    private void setBoardStructure(String s) {
        String[] arr = s.split(":");
        int playerPos = Integer.parseInt(arr[1].charAt(1) + "");
        board.setPlayerNumber(playerPos);
        int[][] b = new int[size][size];
        for(int i=0;i<3;i++){
            String[] blocks = arr[2+i].split(";");
            for(int j=0;j<blocks.length;j++){
                int xPos = Integer.parseInt(blocks[j].split(",")[0]);
                int yPos = Integer.parseInt(blocks[j].split(",")[1]);

                b[xPos][yPos] = i+1;
            }
        }
        board.setBoard(b);
    }

    private void setGlobalUpdate(String s){
         String[] arr = s.split(":");
        int[][] b = board.getBoard();
        int thisPlayerNumber = board.getPlayerNumber();
        for (int i = 0; i < arr.length - 4; i++) {
            String[] player = arr[i + 1].split(";");
            int xPos = Integer.parseInt(player[1].split(",")[0]);
            int yPos = Integer.parseInt(player[1].split(",")[1]);
            int direction = Integer.parseInt(player[2].trim());
            int num = (i + 1) * 10 + direction;
            b[xPos][yPos] = num;
            if (thisPlayerNumber == i + 1) {
                board.setPlayerPositins(new int[]{xPos, yPos, direction});
            }
        }
        board.setBoard(b);
    }
}
