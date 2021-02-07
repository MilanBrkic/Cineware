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
import operation.director.AddDirector;
import operation.director.UpdateDirector;
import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.DbActor;
import repository.db.impl.DbDirector;
import repository.db.impl.DbGeneric;
import repository.db.impl.DbHall;
import repository.db.impl.DbMovie;
import repository.db.impl.DbUser;
/**
 *
 * @author Milan
 */
public class Controller {
    int test;
    private Repository dbUser;
    private Repository dbHall;
    private Repository dbDirector;
    private Repository dbActor;
    private Repository dbMovie;
    private Repository dbGeneric;
    private static Controller instance;
    private ArrayList<String> countries;
    
    private Controller(){
        dbGeneric = new DbGeneric();
        dbUser = new DbUser();
        dbHall = new DbHall();
        dbDirector = new DbDirector();
        dbActor = new DbActor();
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
        ArrayList<Hall> halls = null;
        try {
            halls = dbHall.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return halls;
    }

    public ArrayList<User> getAllUsers() throws Exception {
        ArrayList<User> users = null;
        try {
            users = dbUser.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return users;
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
        AbstractGenericOperation ago = new AddDirector();
        ago.execute(director);
    }
    
    public ArrayList<Director> getAllDirectors()throws Exception{
        ArrayList<Director> directors = null;
        try {
            directors = dbDirector.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return directors;
    }
    
    public void updateDirector(Director director) throws Exception {
        AbstractGenericOperation ago = new UpdateDirector();
        ago.execute(director);
    }
    
    public void deleteDirector(Director director) throws Exception {
        try {
            dbDirector.delete(director);
            ((DbRepository)dbDirector).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DbRepository)dbDirector).rollback();
            throw e;
        }finally{
            ((DbRepository)dbDirector).disconnect();
        }
    }
    
    public Director getDirector(int id)throws Exception{
        Director director = null;
        try {
            director = ((DbDirector)dbDirector).get(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return director;
    }
    
    
    public void addActor(Actor actor) throws Exception {
        try {
            dbActor.add(actor);
            ((DbRepository)dbActor).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DbRepository)dbActor).rollback();
            throw e;
        }finally{
            ((DbRepository)dbActor).disconnect();
        }
    }
    
    public ArrayList<Actor> getAllActors()throws Exception{
        ArrayList<Actor> actors = null;
        try {
            actors = dbActor.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return actors;
    }
    
    public void updateActor(Actor actor) throws Exception {
        try {
            dbActor.update(actor);
            ((DbRepository)dbActor).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DbRepository)dbActor).rollback();
            throw e;
        }finally{
            ((DbRepository)dbActor).disconnect();
        }
    }
    
    public void deleteActor(Actor actor) throws Exception {
        try {
            dbActor.delete(actor);
            ((DbRepository)dbActor).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DbRepository)dbActor).rollback();
            throw e;
        }finally{
            ((DbRepository)dbActor).disconnect();
        }
    }

    public void addMovie(Movie movie) throws Exception {
        try {
            dbMovie.add(movie);
            ((DbRepository)dbMovie).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DbRepository)dbMovie).rollback();
            throw e;
        }finally{
           ((DbRepository)dbActor).disconnect();
        }
    }

    public ArrayList<Movie> getAllMovies() throws Exception {
        ArrayList<Movie> movies = null;
        try {
            movies = dbMovie.getAll();
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
