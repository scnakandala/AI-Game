/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author naka
 */
public class GameClient {

    private static String serverIP = "127.0.0.1";
    private static int port = 6000;
    private static PrintWriter writer;
    private static BufferedReader reader;

    public static boolean establishConectiin() {
        try {
            System.out.println("Trying to connect to the server at " + GameClient.serverIP);
            Socket socket = new Socket(serverIP, port);
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to the server");
            return true;
        } catch (UnknownHostException ex) {
            System.out.println("Unable to connect to the server. UnknownHostException");
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            System.out.println("Unable to connect to the server. IOException");
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean joinTheGame() {
        System.out.println("Trying to join the game");
        try {
            String command = "JOIN#";
            writer.println(command);
            String response = reader.readLine();
            if (response.equals("PLAYERS_FULL#")) {
                System.out.println("Unable to join the game. Players Full");
                return false;
            } else if (response.equals("ALREADY_ADDED#")) {
                System.out.println("Unable to join the game. Already Added");
                return false;
            } else if (response.equals("GAME_ALREADY_STARTED#")) {
                System.out.println("Unable to join the game. Game Already Started");
                return false;
            } else {
                System.out.println("Successfully Joined The Game");
                System.out.println(response);
                return true;
            }
        } catch (IOException ex) {
            System.out.println("Unable to join the game. IOException");
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
