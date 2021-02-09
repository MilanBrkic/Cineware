/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import common.communication.Operation;
import common.communication.Request;
import common.communication.Sender;
import java.net.Socket;

/**
 *
 * @author user
 */
public class HandleExit{
    
    Socket socket;
    Sender sender;

    public HandleExit(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
    }
    
    public void exitClient() throws Exception{
        Request req = new Request(Operation.EXIT_ALL, null);
        sender.send(req);
    }
    
}
