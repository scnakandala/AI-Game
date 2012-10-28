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

    private int playerNumber;
    private int[] playerPositins;
    private int[][] board ;

    public void setPlayerPositins(int[] playerPositins) {
        this.playerPositins = playerPositins;
    }

    public int[] getPlayerPositins() {
        return playerPositins;
    }

    public int getPlayerNumber(){
        return playerNumber;
    }

    public void setPlayerNumber(int num){
        playerNumber=num;
    }
    
    public void setBoard(int[][] arr){
        board = arr;
    }
    
    public int[][] getBoard(){
        return board;
    }

}
