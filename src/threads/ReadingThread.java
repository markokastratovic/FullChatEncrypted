/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.FChat;

/**
 *
 * @author Marko
 */
public class ReadingThread extends Thread {

    private final Socket socket;
    private FChat fChat = null;

    public ReadingThread(Socket socket) {
        super();
        this.socket = socket;
    }

    public void setfChat(FChat fChat) {
        this.fChat = fChat;
    }

    @Override
    public void run() {
        while (true) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                fChat.addRecievedMessage("Other: " + cryptography.Crypto.decrypt(bufferedReader.readLine(), "sdfhsioshjoi4t3e"));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                Logger.getLogger(ReadingThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
