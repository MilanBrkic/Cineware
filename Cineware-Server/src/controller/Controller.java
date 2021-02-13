/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import domain.Actor;
import domain.Article;
import domain.Director;
import domain.Hall;
import domain.Movie;
import domain.Projection;
import domain.Seat;
import domain.Ticket;
import domain.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import operation.AbstractGenericOperation;
import operation.GenericAdd;
import operation.GenericAddWithGenKeys;
import operation.GenericDelete;
import operation.GenericGet;
import operation.GenericGetAll;
import operation.GenericUpdate;
import operation.actor.GetAllActors;
import operation.article.AddArticle;
import operation.director.GetAllDirectors;
import operation.director.GetDirector;
import operation.movie.AddMovie;
import operation.movie.DeleteMovie;
import operation.movie.GetAllMovies;
import operation.movie.GetMovie;
import operation.movie.UpdateMovie;
import operation.projection.GetAllProjections;
import operation.seat.GetAllByHall;
import operation.ticket.AddTickets;
import repository.db.impl.DbProjection;
import operation.user.CheckPassword;
import operation.user.UpdatePasswordOnly;
import operation.user.UpdateWithoutPassword;
import repository.db.DbRepository;
import view.controller.ControllerView;

/**
 *
 * @author Milan
 */
public class Controller {

    int test;
    private static Controller instance;
    private final ArrayList<String> countries;

    private Controller() {
        countries = readCoutries();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

    public ArrayList<String> readCoutries() {
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

    public ArrayList<Hall> getAllHalls() throws Exception {
        AbstractGenericOperation ago = new GenericGetAll<Hall>();
        ago.execute(new Hall());
        return ((GenericGetAll) ago).getResult();
    }
    
    public Hall getHall(int hallID) throws Exception {
        AbstractGenericOperation ago = new GenericGet<Hall>();
        Hall hall = new Hall();
        hall.setId(hallID);
        ago.execute(hall);
        return (Hall) ((GenericGet) ago).getResult();
    }
    
    public ArrayList<Seat> getAllByHall(Hall hall) throws Exception{
        AbstractGenericOperation ago = new GetAllByHall();
        ago.executeWithoutCommit(hall);
        return ((GetAllByHall)ago).getResult();
    }

    public ArrayList<User> getAllUsers() throws Exception {
        AbstractGenericOperation ago = new GenericGetAll<User>();
        ago.execute(new User());
        return ((GenericGetAll) ago).getResult();
    }

    public void addUser(User user) throws Exception {
        AbstractGenericOperation ago = new GenericAdd<User>();
        ago.execute(user);
    }

    public void updateUser(User user) throws Exception {
        AbstractGenericOperation ago = new UpdateWithoutPassword();
        ago.execute(user);
    }

    public void deleteUser(User user) throws Exception {
        AbstractGenericOperation ago = new GenericDelete<User>();
        ago.execute(user);
    }

    public User getUser(int id) throws Exception {
        AbstractGenericOperation ago = new GenericGet<User>();
        User user = new User();
        user.setId(id);
        ago.execute(user);
        return (User) ((GenericGet) ago).getResult();
    }

    public boolean checkPassword(String username, String password) throws Exception {
        AbstractGenericOperation ago = new CheckPassword();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        ago.execute(user);
        return ((CheckPassword) ago).getResult();
    }

    public void updatePasswordOnly(String username, String password) throws Exception {
        AbstractGenericOperation ago = new UpdatePasswordOnly();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        ago.execute(user);
    }

    //director
    public void addDirector(Director director) throws Exception {
        AbstractGenericOperation ago = new GenericAdd<Director>();
        ago.execute(director);
    }

    public ArrayList<Director> getAllDirectors() throws Exception {
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

    public Director getDirector(int id) throws Exception {
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

    public ArrayList<Actor> getAllActors() throws Exception {
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
        AbstractGenericOperation ago = new AddMovie();
        ago.execute(movie);
    }

    public ArrayList<Movie> getAllMovies() throws Exception {
        AbstractGenericOperation ago = new GetAllMovies();
        ago.execute(new Movie());
        return ((GetAllMovies) ago).getResult();
    }

    public Movie getMovie(int id) throws Exception {
        AbstractGenericOperation ago = new GetMovie();
        Movie movie = new Movie();
        movie.setId(id);
        ago.execute(movie);
        return ((GetMovie) ago).getResult();
    }

    public void newLoggedInUser(User user) {
        ControllerView.getInstance().newLoggedInUser(user);
    }

    public void loggedOutUser(User userOut) {
        ControllerView.getInstance().loggedOutUser(userOut);
    }

    public void updateMovie(Movie movie) throws Exception {
        AbstractGenericOperation ago = new UpdateMovie();
        ago.execute(movie);
    }

    public void deleteMovie(Movie movie) throws Exception {
        AbstractGenericOperation ago = new DeleteMovie();
        ago.execute(movie);
    }

    public void addProjection(Projection projection) throws Exception {
        try {
            DbProjection rep = new DbProjection();
            if (!rep.isTheHallOccupied(projection)) {
                throw new Exception("Hall ocuppied in given time");
            }
            AbstractGenericOperation ago = new GenericAddWithGenKeys<Projection>();
            ago.executeWithoutCommit(projection);
            
            addTickets(projection);
        } catch (Exception e) {
            throw e;
        }

    }

    public ArrayList<Projection> getAllProjections() throws Exception {
        AbstractGenericOperation ago = new GetAllProjections();
        ago.execute(new Projection());
        return ((GetAllProjections) ago).getResult();
    }

    public int addArticle(Article article) throws Exception{
        AbstractGenericOperation ago = new AddArticle();
        ago.executeWithoutCommit(article);
        return ((AddArticle)ago).getResult();
    }
    
    
    public void addTicket(Ticket ticket) throws Exception{
        AbstractGenericOperation ago = new GenericAdd<Ticket>();
        ago.executeWithoutCommit(ticket);
    }
    
    public void addTickets(Projection projection) throws Exception{
        AbstractGenericOperation ago = new AddTickets();
        ago.execute(projection);
    }

    public void deleteProjection(Projection projection) throws Exception {
        AbstractGenericOperation ago = new GenericDelete<Ticket>();
        ago.execute(projection);
    }
    

    class Countries {

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
