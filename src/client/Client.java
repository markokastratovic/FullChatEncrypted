/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.net.Socket;
import threads.ReadingThread;
import threads.WritingThread;
import ui.FChat;

/**
 *
 * @author Marko
 */
public class Client {

    private Socket socket;
    private FChat gui;

    public static void main(String[] args) {
        Client client = new Client();
        client.connect();
    }

    public Client() {
        prepareGui();
    }

    public void prepareGui() {
        gui = new FChat();
        gui.setVisible(true);
        gui.getBtnSend().setEnabled(true);
        gui.setTitle("Client");
    }

    private void connect() {
        try {
            socket = new Socket("localhost", 8555);
            ReadingThread readingThread = new ReadingThread(socket);
            readingThread.start();
            WritingThread writtingThread = new WritingThread(socket);
            writtingThread.start();
            gui.setWritingThread(writtingThread);
            writtingThread.setfChat(gui);
            readingThread.setfChat(gui);

            readingThread.join();
            writtingThread.join();
            System.out.println("End");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
