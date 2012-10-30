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
        System.out.println(moveUpwards);
        return writer.sendMessage(moveUpwards);
    }

    public boolean moveDownwards() {
        System.out.println(moveDownwards);
        return writer.sendMessage(moveDownwards);
    }

    public boolean moveRight() {
        System.out.println(moveRight);
        return writer.sendMessage(moveRight);
    }

    public boolean moveLeft() {
        System.out.println(moveLeft);
        return writer.sendMessage(moveLeft);
    }

    public boolean shoot() {
        return writer.sendMessage(shoot);
    }

    public void setNewMessage(String message) {
        if (message.equals(playersFull)) {
            //System.out.println("Players Full");
        } else if (message.equals(gameAlreadyStarted)) {
            //System.out.println("Game Already Started");
        } else if (message.equals(alreadyAdded)) {
            //System.out.println("Trying to join the game.But have already joined the game");
        } else if (message.startsWith("I:")) {
            //System.out.println("Player registered for the game");
            setBoardStructure(message);
        } else if (message.startsWith("S:")) {
            //System.out.println("Game Started");
            updateOtherPlayers(message);
        } else if (message.startsWith("G:")) {
            //System.out.println("Global update recieved");
            setGlobalUpdate(message);
        } else if (message.startsWith("C:")) {
            //System.out.println("Coin pile appeared");
            setCoinPile(message);
        } else if (message.startsWith("L:")) {
            //System.out.println("Life pack appeared");
            setLifePack(message);
        } else if (message.equals(gameOver)) {
            //System.out.println("Game finished");
        } else {
            System.out.println("Someother message appeared " + message);
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
            if (thisPlayerNumber == i) {
                board.setPlayerPositins(new int[]{xPos, yPos, direction});
            }
        }
        board.setBoard(b);

        setChanged();
        notifyObservers("PLAYER_REGISTERED");
    }

    private void setBoardStructure(String s) {
        String[] arr = s.split(":");
        int playerPos = Integer.parseInt(arr[1].charAt(1) + "");
        board.setPlayerNumber(playerPos);
        int[][] b = new int[Board.size][Board.size];
        for (int i = 0; i < 3; i++) {
            String[] blocks = arr[2 + i].split(";");
            for (int j = 0; j < blocks.length; j++) {
                int xPos = Integer.parseInt(blocks[j].split(",")[0]);
                int yPos = Integer.parseInt(blocks[j].split(",")[1]);

                b[xPos][yPos] = i + 4;
            }
        }
        board.setBoard(b);

        setChanged();
        notifyObservers("GAME_STARTED");
    }

    private void setGlobalUpdate(String s) {
        String[] arr = s.split(":");
        int[][] b = board.getBoard();
        int thisPlayerNumber = board.getPlayerNumber();
        int[][] playerStats = new int[arr.length - 2][7];
        for (int i = 0; i < arr.length - 2; i++) {
            String[] player = arr[i + 1].split(";");
            int xPos = Integer.parseInt(player[1].split(",")[0]);
            int yPos = Integer.parseInt(player[1].split(",")[1]);
            int direction = Integer.parseInt(player[2].trim());
            int shot = Integer.parseInt(player[3].trim());
            int health = Integer.parseInt(player[4].trim());
            int coins = Integer.parseInt(player[5].trim());
            int points = Integer.parseInt(player[6].trim());

            int num = (i + 1) * 10 + direction + shot * 4;

            playerStats[i] = new int[]{xPos, yPos, direction, shot, health, coins, points};
            b[xPos][yPos] = num;
            if (thisPlayerNumber == i) {
                board.setPlayerPositins(new int[]{xPos, yPos, direction});
            }
        }

        // ! change of bricks has nt considered yet
        board.setPlayerStats(playerStats);
        board.setBoard(b);

        setChanged();
        notifyObservers("GLOBAL_UPDATE");
    }

    public void setCoinPile(String s){
        String[] arr = s.split(":");
        int x = Integer.parseInt(arr[1].split(",")[0]);
        int y = Integer.parseInt(arr[1].split(",")[1]);
        int lt = Integer.parseInt(arr[2].trim());
        int val = Integer.parseInt(arr[3].trim());

        board.addCoinPile(new Integer[]{x,y,lt,val});
    }

    public void setLifePack( String s){
        String[] arr = s.split(":");
        int x = Integer.parseInt(arr[1].split(",")[0]);
        int y = Integer.parseInt(arr[1].split(",")[1]);
        int lt = Integer.parseInt(arr[2].trim());

        board.addLifePacks(new Integer[]{x,y,lt});
    }
}
