/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author naka
 */
public class Board {

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


    public static final int LIFE_PACK = 500;
    public static final int COINS = 100;


    public static final int size = 10;


    private int playerNumber;
    private int[] playerPositins;
    private int[][] board;
    private int[][] playerStats;

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
}
