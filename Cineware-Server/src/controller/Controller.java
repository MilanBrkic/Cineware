/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import domain.Actor;
import domain.Director;
import domain.Hall;
import domain.Movie;
import domain.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import operation.AbstractGenericOperation;
import operation.GenericAdd;
import operation.GenericDelete;
import operation.GenericGetAll;
import operation.GenericUpdate;
import operation.actor.GetAllActors;
import operation.director.GetAllDirectors;
import operation.director.GetDirector;
import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.DbMovie;
import repository.db.impl.DbUser;
/**
 *
 * @author Milan
 */
public class Controller {
    int test;
    private Repository dbUser;
    private Repository dbMovie;
    private static Controller instance;
    private final ArrayList<String> countries;
    
    private Controller(){
        dbUser = new DbUser();
        dbMovie = new DbMovie();
        countries = readCoutries();
    }
    
    public static Controller getInstance(){
        if(instance==null) instance = new Controller();
        return instance;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }
       
    public ArrayList<String> readCoutries(){
        ArrayList<String> list = new ArrayList<>();
        try {
            
            String basePath = new File("").getAbsolutePath();
            String jsonPath = basePath + "\\resources\\countries.json";
            
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(jsonPath));
            
            List<Countries> coutries = Arrays.asList(gson.fromJson(reader, Countries[].class));
            
            for (Countries coutry : coutries) {
                list.add(coutry.getName().trim());
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Hall> getAllHalls() throws Exception{
        AbstractGenericOperation ago = new GenericGetAll<Hall>();
        ago.execute(new Hall());
        return ((GenericGetAll)ago).getResult();
    }

    public ArrayList<User> getAllUsers() throws Exception {
        AbstractGenericOperation ago = new GenericGetAll<User>();
        ago.execute(new User());
        return ((GenericGetAll)ago).getResult();
    }

    public void addUser(User user) throws  Exception{
         try {
            dbUser.add(user);
            ((DbRepository)dbUser).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DbRepository)dbUser).rollback();
            throw e;
        }finally{
            ((DbRepository)dbUser).disconnect();
        }
    }
    
    
    public void updateUser(User user) throws Exception {
        try {
            dbUser.update(user);
            ((DbRepository)dbUser).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DbRepository)dbUser).rollback();
            throw e;
        }finally{
            ((DbRepository)dbUser).disconnect();
        }
    }
    
    
    public void deleteUser(User user) throws Exception {
        try {
            dbUser.delete(user);
            ((DbRepository)dbUser).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DbRepository)dbUser).rollback();
            throw e;
        }finally{
            ((DbRepository)dbUser).disconnect();
        }
    }

    public User getUser(int id) throws Exception{
        User user = null;
        try {
            user = ((DbUser)dbUser).getUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return user;
    }
    
    
    public boolean checkPassword(String username,String password) throws Exception{
        try {
            return ((DbUser)dbUser).checkPassword(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    
    public void updatePasswordOnly(String username, String password) throws SQLException{
        try {
            ((DbUser)dbUser).updatePasswordOnly(username, password);
            ((DbRepository)dbUser).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DbRepository)dbUser).rollback();
            throw e;
        }finally{
            ((DbRepository)dbUser).disconnect();
        }
    }

    public void addDirector(Director director) throws Exception {
        AbstractGenericOperation ago = new GenericAdd<Director>();
        ago.execute(director);
    }
    
    public ArrayList<Director> getAllDirectors()throws Exception{
       AbstractGenericOperation ago = new GetAllDirectors();
       ago.execute(new Director()); 
       return ((GetAllDirectors) ago).getResult();
    }
    
    public void updateDirector(Director director) throws Exception {
        AbstractGenericOperation ago = new GenericUpdate<Director>();
        ago.execute(director);
    }
    
    public void deleteDirector(Director director) throws Exception {
        AbstractGenericOperation ago = new GenericDelete<Director>();
        ago.execute(director);
    }
    
    public Director getDirector(int id)throws Exception{
       AbstractGenericOperation ago = new GetDirector();
       Director dir = new Director();
       dir.setId(id);
       ago.execute(dir); 
       return ((GetDirector) ago).getResult();
    }
    
    //actor
    
    public void addActor(Actor actor) throws Exception {
        AbstractGenericOperation ago = new GenericAdd<Actor>();
        ago.execute(actor);
    }
    
    public ArrayList<Actor> getAllActors()throws Exception{
       AbstractGenericOperation ago = new GetAllActors();
       ago.execute(new Actor()); 
       return ((GetAllActors) ago).getResult();
    }
    
    public void updateActor(Actor actor) throws Exception {
        AbstractGenericOperation ago = new GenericUpdate<Actor>();
        ago.execute(actor);
    }
    
    public void deleteActor(Actor actor) throws Exception {
        AbstractGenericOperation ago = new GenericDelete<Actor>();
        ago.execute(actor);
    }
    
    //movie

    public void addMovie(Movie movie) throws Exception {
        try {
            dbMovie.add(movie);
            ((DbRepository)dbMovie).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DbRepository)dbMovie).rollback();
            throw e;
        }finally{
           ((DbRepository)dbMovie).disconnect();
        }
    }

    public ArrayList<Movie> getAllMovies() throws Exception {
        ArrayList<Movie> movies = null;
        try {
            movies = ((DbMovie)dbMovie).getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return movies;
    }
    
    
    
    
    class Countries{
        String name;

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
