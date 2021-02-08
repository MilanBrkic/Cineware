/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import thread.HandleThread;

/**
 *
 * @author user
 */
public class Server extends Thread {

    ServerSocket serverSocket;
    HandleThread[] clients = new HandleThread[10];

    public Server() {
        start();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
            for (int i = 0; i < clients.length; i++) {
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                clients[i] = new HandleThread(socket);
                clients[i].start();
            }

        } catch (Exception ex) {
        }
        System.out.println("Server stopped.");
    }

    public void close() throws IOException {
        serverSocket.close();
        
    }

    
}
