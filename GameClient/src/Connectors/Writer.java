/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connectors;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Supun Nakandala
 */
public class Writer {

    private String serverIP;
    private int port;
    private Socket server;
    private BufferedWriter writer;

    public Writer() {
        this.serverIP = "127.0.0.1";
        port = 6000;
    }

    public boolean sendMessage(String message) {
        if (server == null) {
            if (!connect()) {
                return false;
            }
        }
        try {
            writer = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
            writer.write(message);
            writer.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean connect() {
        if (server == null) {
            try {
                server = new Socket(serverIP, port);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (IOException ex) {
                Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }
}
