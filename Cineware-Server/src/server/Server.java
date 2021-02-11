/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.SpringLayout;
import thread.HandleExit;
import thread.HandleThread;

/**
 *
 * @author user
 */
public class Server extends Thread {

    ServerSocket serverSocket;
    HandleThread[] clients = new HandleThread[10];
    HandleExit[] exits = new HandleExit[10];

    public Server() {
        start();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
            
            while (true) {
                int brojac = 0;
                for (int i = 0; i < clients.length; i++) {
                    if (clients[i] == null) {
                        System.out.println("Waiting for connection...");
                        Socket socket = serverSocket.accept();
                        Socket socketForExit = serverSocket.accept();
                        System.out.println("Client connected!");
                        exits[i] = new HandleExit(socketForExit);
                        clients[i] = new HandleThread(socket, clients, exits);
                        clients[i].start();
                        print();
                        brojac++;
                    }
                }
                if(brojac==0) break;
                
            }

        } catch (Exception ex) {
        }
        System.out.println("Server stopped");
    }

    public void print() {
        System.out.print("HandleThread: ");
        for (int i = 0; i < clients.length; i++) {
            System.out.print(clients[i] + " ");
        }
        System.out.println("");
        
        System.out.print("ExitThread: ");
        for (int i = 0; i < exits.length; i++) {
            System.out.print(exits[i] + " ");
        }
        System.out.println("");
    }

    public void close() throws Exception {
        for (int i = 0; i < exits.length; i++) {
            if (exits[i] != null) {
                exits[i].exitClient();
            }
        }

        serverSocket.close();
    }

}
