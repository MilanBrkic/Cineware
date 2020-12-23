/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communcation;

import common.communication.Operation;
import common.communication.Receiver;
import common.communication.Request;
import common.communication.Response;
import common.communication.Sender;
import domain.Hall;
import domain.User;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Milan
 */
public class Communcation {
    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private static Communcation instance;
    
    
    private Communcation() throws IOException {
        this.socket = new Socket("localhost", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public static Communcation getInstance() throws IOException {
        if(instance==null) instance=new Communcation();
        return instance;
    }
    
    public void closeSocket() throws IOException{
        socket.close();
    }
    
    public ArrayList<String> getCountries() throws Exception {
        Request request = new Request(Operation.GET_COUNTRIES, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (ArrayList<String>) response.getResult();
        }
        else throw response.getException();
    }
    
    
    public ArrayList<Hall> getAllHalls() throws Exception{
        Request request = new Request(Operation.GET_ALL_HALLS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (ArrayList<Hall>) response.getResult();
        }
        else throw response.getException();
    }
    
    
    public ArrayList<User> getAllUsers() throws Exception {
        Request request = new Request(Operation.GET_ALL_USERS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (ArrayList<User>) response.getResult();
        }
        else throw response.getException();
    }
    
    
    public void addUser(User user) throws  Exception{
        Request request = new Request(Operation.ADD_USER, user);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            
        }
        else throw response.getException();
    }
    
    public void updateUser(User user) throws Exception {
        Request request = new Request(Operation.UPDATE_USER, user);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            
        }
        else throw response.getException();
    }
    
    public void deleteUser(User user) throws Exception {
        Request request = new Request(Operation.DELETE_USER, user);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            
        }
        else throw response.getException();
    }
    
    public boolean checkPassword(String username,String password) throws Exception{
       User user = new User();
       user.setUsername(username);
       user.setPassword(password);
       Request request = new Request(Operation.CHECK_PASSWORD, user);
       sender.send(request);
       Response response = (Response) receiver.receive();
       if(response.getException()==null){
           return (boolean) response.getResult();
       }
       else throw response.getException();
    }
    
    public void updatePasswordOnly(String username, String password) throws Exception{
       User user = new User();
       user.setUsername(username);
       user.setPassword(password);
       Request request = new Request(Operation.UPDATE_PASSWORD_ONLY, user);
       sender.send(request);
       Response response = (Response) receiver.receive();
       if(response.getException()==null){
       }
       else throw response.getException();
    }
}
