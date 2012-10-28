/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connectors;

import MessageParser.MessageParser;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Supun Nakandala
 */
public class Reader implements Runnable{

    private int port;
    private ServerSocket clientSocket;
    private MessageParser parser;

    public Reader(MessageParser p) {
        port = 7000;
        parser = p;
    }

    public boolean initialise() {
        if (clientSocket == null) {
            try {
                clientSocket = new ServerSocket(port);
            } catch (IOException ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }

    public void run() {
        getMessage();
    }

    private void getMessage() {
        while (true) {
            if (clientSocket == null) {
                if (!initialise()) {
                    break;
                }
            }
            try {
                byte[] buffer = new byte[250];
                Socket socket = clientSocket.accept();
                InputStream in = socket.getInputStream();
                in.read(buffer);
                String s = (new String(buffer)).split("#")[0];
                parser.setNewMessage(s);
                System.out.println(s);
            } catch (IOException ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }
}
