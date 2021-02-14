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
import domain.Actor;
import domain.Director;
import domain.Hall;
import domain.Movie;
import domain.Product;
import domain.Projection;
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
    private Socket socketForExit;
    private Sender sender;
    private Receiver receiver;
    private static Communcation instance;
    private Gson gson;
    
    private Communcation() throws IOException {
        this.socket = new Socket("localhost", 9000);
        socketForExit =  new Socket("localhost", 9000);
        WaitForExit wait = new WaitForExit(socketForExit);
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
    
    
    //HALL
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
    
    //USER
    
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
    
    public void login(User user) throws Exception{
        String s = gson.toJson(user);
        Request request = new Request(Operation.LOGIN, s);
        sender.send(request);
        receiver.receive();
    }
    
    public void logout(User user) throws Exception {
        String s = gson.toJson(user);
        Request request = new Request(Operation.LOGOUT, s);
        sender.send(request);
        receiver.receive();
    }
    
    public void addUser(User user) throws  Exception{
        GenericAddUpdateDelete<User> gen = new GenericAddUpdateDelete<>(gson,sender,receiver);
        gen.execute(user, Operation.ADD_USER);
    }
    
    public void updateUser(User user) throws Exception {
        GenericAddUpdateDelete<User> gen = new GenericAddUpdateDelete<>(gson,sender,receiver);
        gen.execute(user, Operation.UPDATE_USER);
    }
    
    public void deleteUser(User user) throws Exception {
        GenericAddUpdateDelete<User> gen = new GenericAddUpdateDelete<>(gson,sender,receiver);
        gen.execute(user, Operation.DELETE_USER);
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

    //DIRECTOR
    
    public void addDirector(Director director) throws Exception {
        GenericAddUpdateDelete<Director> gen = new GenericAddUpdateDelete<>(gson,sender,receiver);
        gen.execute(director, Operation.ADD_DIRECTOR);
    }
    
    
    public void updateDirector(Director director) throws Exception {
        GenericAddUpdateDelete<Director> gen = new GenericAddUpdateDelete<>(gson,sender,receiver);
        gen.execute(director, Operation.UPDATE_DIRECTOR);
    }
    
    public void deleteDirector(Director director) throws Exception {
        GenericAddUpdateDelete<Director> gen = new GenericAddUpdateDelete<>(gson,sender,receiver);
        gen.execute(director, Operation.DELETE_DIRECTOR);
    }
    
    public ArrayList<Director> getAllDirectors()throws Exception{
        Request request = new Request(Operation.GET_ALL_DIRECTORS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            String s = response.getResult();
            ArrayList<Director> directors = new ArrayList<>(Arrays.asList(gson.fromJson(s, Director[].class)));
            return directors;
        }
        else throw response.getException();
    }
    
    
    //ACTOR
    
    
    public void addActor(Actor actor) throws Exception {
        GenericAddUpdateDelete<Actor> gen = new GenericAddUpdateDelete<>(gson,sender,receiver);
        gen.execute(actor, Operation.ADD_ACTOR);
    }
    
    
    public void updateActor(Actor actor) throws Exception {
        GenericAddUpdateDelete<Actor> gen = new GenericAddUpdateDelete<>(gson,sender,receiver);
        gen.execute(actor, Operation.UPDATE_ACTOR);
    }
    
    
    
    public void deleteActor(Actor actor) throws Exception {
        GenericAddUpdateDelete<Actor> gen = new GenericAddUpdateDelete<>(gson,sender,receiver);
        gen.execute(actor, Operation.DELETE_ACTOR);
    }
    
    public ArrayList<Actor> getAllActors()throws Exception{
        Request request = new Request(Operation.GET_ALL_ACTORS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            String s = response.getResult();
            ArrayList<Actor> actors = new ArrayList<>(Arrays.asList(gson.fromJson(s, Actor[].class)));
            return actors;
        }
        else throw response.getException();
    }
 

    public ArrayList<Movie> getAllMovies() throws Exception{
        Request request = new Request(Operation.GET_ALL_MOVIES, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            String json = response.getResult();
            ArrayList<Movie> movies = new ArrayList<>(Arrays.asList(gson.fromJson(json, Movie[].class)));
            return movies;
        }else throw response.getException();
    }


    public void addMovie(Movie movie) throws Exception {
        GenericAddUpdateDelete<Movie> gen = new GenericAddUpdateDelete<>(gson,sender,receiver);
        gen.execute(movie, Operation.ADD_MOVIE);
    }
    
    public void updateMovie(Movie movie) throws Exception {
        GenericAddUpdateDelete<Movie> gen = new GenericAddUpdateDelete<>(gson,sender,receiver);
        gen.execute(movie, Operation.UPDATE_MOVIE);
    }

    public void deleteMovie(Movie movie) throws Exception {
       GenericAddUpdateDelete<Movie> gen = new GenericAddUpdateDelete<>(gson,sender,receiver);
       gen.execute(movie, Operation.DELETE_MOVIE);
    }

    public void addProjection(Projection projection) throws Exception {
       GenericAddUpdateDelete<Projection> gen = new GenericAddUpdateDelete<>(gson,sender,receiver);
       gen.execute(projection, Operation.ADD_PROJECTION);
    }

    public ArrayList<Projection> getAllProjections() throws Exception {
        Request request = new Request(Operation.GET_ALL_PROJECTIONS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            String json = response.getResult();
            ArrayList<Projection> projections = new ArrayList<>(Arrays.asList(gson.fromJson(json, Projection[].class)));
            return projections;
        }else throw response.getException();
    }

    public void deleteProjection(Projection projection) throws Exception {
       GenericAddUpdateDelete<Projection> gen = new GenericAddUpdateDelete<>(gson,sender,receiver);
       gen.execute(projection, Operation.DELETE_PROJECTION);
    }

    public void addProduct(Product product) throws Exception {
        GenericAddUpdateDelete<Product> gen = new GenericAddUpdateDelete<>(gson,sender,receiver);
       gen.execute(product, Operation.ADD_PRODUCT);
    }

}
