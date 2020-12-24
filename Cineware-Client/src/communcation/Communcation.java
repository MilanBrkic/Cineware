/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communcation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.communication.Operation;
import common.communication.Receiver;
import common.communication.Request;
import common.communication.Response;
import common.communication.Sender;
import domain.Director;
import domain.Hall;
import domain.User;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Milan
 */
public class Communcation {
    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private static Communcation instance;
    private Gson gson;
    
    private Communcation() throws IOException {
        this.socket = new Socket("localhost", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
        gson = new GsonBuilder().setPrettyPrinting().create();
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
            String s = response.getResult();
            ArrayList<String> countries = new ArrayList<>(Arrays.asList(gson.fromJson(s, String[].class)));
            return countries;
        }
        else throw response.getException();
    }
    
    
    public ArrayList<Hall> getAllHalls() throws Exception{
        Request request = new Request(Operation.GET_ALL_HALLS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            String s = response.getResult();
            ArrayList<Hall> halls = new ArrayList<>(Arrays.asList(gson.fromJson(s, Hall[].class)));
            return halls;
        }
        else throw response.getException();
    }
    
    
    public ArrayList<User> getAllUsers() throws Exception {
        Request request = new Request(Operation.GET_ALL_USERS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            String s = response.getResult();
            ArrayList<User> users = new ArrayList<>(Arrays.asList(gson.fromJson(s, User[].class)));
            return users;
        }
        else throw response.getException();
    }
    
    
    public void addUser(User user) throws  Exception{
        String s = gson.toJson(user);
        Request request = new Request(Operation.ADD_USER, s);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            
        }
        else throw response.getException();
    }
    
    public void updateUser(User user) throws Exception {
        String s = gson.toJson(user);
        Request request = new Request(Operation.UPDATE_USER, s);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            
        }
        else throw response.getException();
    }
    
    public void deleteUser(User user) throws Exception {
        String s = gson.toJson(user);
        Request request = new Request(Operation.DELETE_USER, s);
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
       String s = gson.toJson(user);
       Request request = new Request(Operation.CHECK_PASSWORD, s);
       sender.send(request);
       Response response = (Response) receiver.receive();
       if(response.getException()==null){
           String json = response.getResult();
           boolean bu = gson.fromJson(json, Boolean.class);
           return bu;
       }
       else throw response.getException();
    }
    
    public void updatePasswordOnly(String username, String password) throws Exception{
       User user = new User();
       user.setUsername(username);
       user.setPassword(password);
       String s = gson.toJson(user);
       Request request = new Request(Operation.UPDATE_PASSWORD_ONLY, s);
       sender.send(request);
       Response response = (Response) receiver.receive();
       if(response.getException()==null){
       }
       else throw response.getException();
    }

    public void addDirector(Director director) throws Exception {
        String s = gson.toJson(director);
        Request request = new Request(Operation.ADD_DIRECTOR, s);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
        }
        else throw response.getException();
    }
}
