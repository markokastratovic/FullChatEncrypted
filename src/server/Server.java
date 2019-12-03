/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.ServerSocket;
import java.net.Socket;
import threads.ReadingThread;
import threads.WritingThread;
import ui.FChat;

/**
 *
 * @author Marko
 */
public class Server {

    private ServerSocket serverSocket;
    private FChat gui;

    public Server() {
        prepareGui();
    }

    public void prepareGui() {
        gui = new FChat();
        gui.setVisible(true);
        gui.getBtnSend().setEnabled(true);
        gui.setTitle("Server");
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void start() throws Exception {
        serverSocket = new ServerSocket(8555);
        System.out.println("Waiting clients");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");
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

    }
}
