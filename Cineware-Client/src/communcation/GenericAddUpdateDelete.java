/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communcation;

import common.communication.Operation;
import domain.GenericEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.communication.Receiver;
import common.communication.Request;
import common.communication.Response;
import common.communication.Sender;

/**
 *
 * @author user
 */
public class GenericAddUpdateDelete<T> {
    
    Gson gson;
    Sender sender;
    Receiver receiver;

    public GenericAddUpdateDelete(Gson gson, Sender sender, Receiver receiver) {
        this.gson = gson;
        this.sender = sender;
        this.receiver = receiver;
    }

    
    
    public void execute(GenericEntity g, Operation operation) throws Exception{
        String s = gson.toJson((T)g);
        Request request = new Request(operation, s);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            
        }
        else throw response.getException();
    }
}
