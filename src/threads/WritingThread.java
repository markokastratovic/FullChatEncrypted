/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.FChat;

/**
 *
 * @author Marko
 */
public class WritingThread extends Thread {

    private final Socket socket;
    private FChat fChat = null;
    boolean messageReady = false;

    public WritingThread(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            messageReady = false;
            while (true) {
                if (messageReady) {
                    //printWriter.println(fChat.getTxtMessage().getText());
                    printWriter.println(cryptography.Crypto.encrypt(fChat.getTxtMessage().getText(), "sdfhsioshjoi4t3e"));
                    fChat.addSentMessage("Me: " + fChat.getTxtMessage().getText());
                    messageReady = false;
                }
                Thread.sleep(200);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(WritingThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(WritingThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void messageReady() {
        messageReady = true;
    }

    public void setfChat(FChat fChat) {
        this.fChat = fChat;
    }

}
