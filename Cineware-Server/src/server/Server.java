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
public class Server {
    public void startServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            while(true){
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                handleClient(socket);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleClient(Socket socket) {
        HandleThread ht = new HandleThread(socket);
        ht.start();
    }
}
