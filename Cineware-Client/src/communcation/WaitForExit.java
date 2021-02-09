/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communcation;

import common.communication.Operation;
import common.communication.Receiver;
import common.communication.Request;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class WaitForExit extends Thread{
    Socket socket;
    Receiver receiver;

    public WaitForExit(Socket socket) {
        this.socket = socket;
        receiver = new Receiver(socket);
        start();
    }

    @Override
    public void run() {
        try {
            Request req = (Request) receiver.receive();
            Operation op = req.getOperation();
            if(op==Operation.EXIT_ALL){
                System.exit(0);
            }
        } catch (Exception ex) {
            Logger.getLogger(WaitForExit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
