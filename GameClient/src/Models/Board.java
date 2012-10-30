/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import MessageParser.MessageParser;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author naka
 */
public class Board implements Observer {

    public static final int EMPTY = 0;
    public static final int BRICK25 = 1;
    public static final int BRICK50 = 2;
    public static final int BRICK75 = 3;
    public static final int BRICK100 = 4;
    public static final int WATER = 5;
    public static final int STONE = 6;
    public static final int P0_MOVE_UP = 10;
    public static final int PO_MQVE_RIGHT = 11;
    public static final int P0_MOVE_DOWN = 12;
    public static final int P0_MOVE_LEFT = 13;
    public static final int P0_MOVE_UP_SHOOT = 14;
    public static final int PO_MQVE_RIGHT_SHOOT = 15;
    public static final int P0_MOVE_DOWN_SHOOT = 16;
    public static final int P0_MOVE_LEFT_SHOOT = 17;
    public static final int P1_MOVE_UP = 20;
    public static final int P1_MQVE_RIGHT = 21;
    public static final int P1_MOVE_DOWN = 22;
    public static final int P1_MOVE_LEFT = 23;
    public static final int P1_MOVE_UP_SHOOT = 24;
    public static final int P1_MQVE_RIGHT_SHOOT = 25;
    public static final int P1_MOVE_DOWN_SHOOT = 26;
    public static final int P1_MOVE_LEFT_SHOOT = 27;
    public static final int P2_MOVE_UP = 30;
    public static final int P2_MQVE_RIGHT = 31;
    public static final int P2_MOVE_DOWN = 32;
    public static final int P2_MOVE_LEFT = 33;
    public static final int P2_MOVE_UP_SHOOT = 34;
    public static final int P2_MQVE_RIGHT_SHOOT = 35;
    public static final int P2_MOVE_DOWN_SHOOT = 36;
    public static final int P2_MOVE_LEFT_SHOOT = 37;
    public static final int P3_MOVE_UP = 40;
    public static final int P3_MQVE_RIGHT = 41;
    public static final int P3_MOVE_DOWN = 42;
    public static final int P3_MOVE_LEFT = 43;
    public static final int P3_MOVE_UP_SHOOT = 44;
    public static final int P3_MQVE_RIGHT_SHOOT = 45;
    public static final int P3_MOVE_DOWN_SHOOT = 46;
    public static final int P3_MOVE_LEFT_SHOOT = 47;
    public static final int P4_MOVE_UP = 50;
    public static final int P4_MQVE_RIGHT = 51;
    public static final int P4_MOVE_DOWN = 52;
    public static final int P4_MOVE_LEFT = 53;
    public static final int P4_MOVE_UP_SHOOT = 54;
    public static final int P4_MQVE_RIGHT_SHOOT = 55;
    public static final int P4_MOVE_DOWN_SHOOT = 56;
    public static final int P4_MOVE_LEFT_SHOOT = 57;
    /**
     * Life pack is suppose to contain a value between 100 - 200
     * Coin pile is suppose to contain a value greater than 1000
     */
    /**
     * Size of the Game Board. The Game Board is assumed to be square.
     */
    public static final int size = 10;
    /**
     * This player's player number. This number is between 0 and 4.
     */
    private int playerNumber;
    /**
     * A 2d int array where 0th position contains the current x position and the
     * 1st position contains the current y position & 2nd position contains the
     * direction
     */
    private int[] playerPositins;
    /**
     * This contains the game board.
     */
    private int[][] board;
    /**
     * A 2d array(m*n) where m is the number of players and n is 7
     * 1-> player x position
     * 2-> player y position
     * 3-> player direction
     *      0->North
     *      1->East
     *      2->South
     *      3->West
     * 4-> Whether player shot
     *      0->No
     *      1->Yes
     * 5->Player Health
     * 6->Player Coins
     * 7->Player Points
     */
    private int[][] playerStats;
    private ArrayList<Integer[]> coinPiles = new ArrayList<Integer[]>();
    private ArrayList<Integer[]> lifePacks = new ArrayList<Integer[]>();

    public int[][] getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(int[][] playerStats) {
        this.playerStats = playerStats;
    }

    public void setPlayerPositins(int[] playerPositins) {
        this.playerPositins = playerPositins;
    }

    public int[] getPlayerPositins() {
        return playerPositins;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int num) {
        playerNumber = num;
    }

    public void setBoard(int[][] arr) {
        board = arr;
    }

    public int[][] getBoard() {
        return board;
    }

    public void addCoinPile(Integer[] arr) {
        coinPiles.add(arr);
        board[arr[0]][arr[1]] = 1000*arr[3]+arr[2];
    }

    public void addLifePacks(Integer[] arr) {
        lifePacks.add(arr);
        board[arr[0]][arr[1]] = 100+ arr[2];
    }

    public void update(Observable o, Object arg) {
        if (o instanceof MessageParser) {
            if (arg instanceof String) {
                if (((String) arg).equals("GLOBAL_UPDATE")) {
                    updateCoinPiles();
                    updateLifePacks();
                }
            }
        }
    }

    private void updateCoinPiles() {
        for(int i=0;i<coinPiles.size();i++){
            if(coinPiles.get(i)[2]==1){
                Integer[] arr = coinPiles.remove(i);
                i--;
                board[arr[0]][arr[1]]=0;
            }else{
                Integer[] arr = coinPiles.remove(i);
                arr[2] = arr[2]-1;
                coinPiles.add(i, arr);
            }
        }
    }

    private void updateLifePacks() {
        for(int i=0;i<lifePacks.size();i++){
            if(lifePacks.get(i)[2]==1){
                Integer[] arr =lifePacks.remove(i);
                i--;
                board[arr[0]][arr[1]]=0;
            }else{
                Integer[] arr = lifePacks.remove(i);
                arr[2] = arr[2]-1;
                lifePacks.add(i, arr);
            }
        }
    }
}
