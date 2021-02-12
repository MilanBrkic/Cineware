/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import controller.Controller;
import domain.Actor;
import domain.Director;
import domain.Genre;
import domain.Movie;
import domain.User;
import java.util.ArrayList;
import repository.db.DbRepository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author user
 */
public class DbMovie implements DbRepository<Movie> {

    public ArrayList<Movie> getAll() throws Exception {
        ArrayList<Movie> movies = new ArrayList<>();
        String query = "SELECT * from movie order by name";
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("movieID");
            String name = rs.getString("name");
            String description = rs.getString("description");
            Genre genre = Genre.valueOf(rs.getString("genre"));
            int runtime = rs.getInt("runtime");
            int year = rs.getInt("year");
            Director director = Controller.getInstance().getDirector(rs.getInt("directorID"));
            User user = Controller.getInstance().getUser(rs.getInt("userID"));
            ArrayList<Actor> actors = getActorsByMovie(id);
            Movie movie = new Movie(id, name, description, genre, runtime, year, director, actors, user);
            movies.add(movie);
        }

        s.close();
        rs.close();

        return movies;
    }

    @Override
    public void add(Movie movie) throws Exception {
        String query = "INSERT INTO movie(name, description, genre, runtime, year, directorID, userID) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = connect().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, movie.getName());
        ps.setString(2, movie.getDescription());
        ps.setString(3, movie.getGenre().toString());
        ps.setInt(4, movie.getRuntime());
        ps.setInt(5, movie.getYear());
        ps.setInt(6, movie.getDirector().getId());
        ps.setInt(7, movie.getUser().getId());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int id = rs.getInt(1);
        System.out.println(id);
        ps.close();

        addActorsForMovies(id, movie.getActors());
    }

    private void addActorsForMovies(int id, ArrayList<Actor> actors) throws Exception {
        String query = "INSERT INTO movie_actor(movieID,actorID) VALUES(?,?)";
        PreparedStatement ps = connect().prepareStatement(query);
        ps.setInt(1, id);
        for (Actor actor : actors) {
            ps.setInt(2, actor.getId());
            ps.executeUpdate();
        }
        ps.close();
    }

    private ArrayList<Actor> getActorsByMovie(int movieID) throws Exception {
        ArrayList<Actor> actors = new ArrayList<>();
        String query = "SELECT a.actorID,a.firstname,a.lastname,a.dateOfBirth, a.nationality,a.userID FROM movie_actor ma INNER JOIN actor a ON a.actorID=ma.actorID WHERE ma.movieID=" + movieID;
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("actorID");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            Date date = new Date(rs.getDate("dateOfBirth").getTime());
            String nationality = rs.getString("nationality");
            User user = Controller.getInstance().getUser(rs.getInt("userID"));
            Actor actor = new Actor(id, firstname, lastname, date, nationality, user);
            actors.add(actor);
        }

        s.close();
        rs.close();
        return actors;
    }

    @Override
    public void update(Movie movie) throws Exception {
        deleteAllActorsFromMovie(movie.getId());
        addActorsForMovies(movie.getId(), movie.getActors());
        String query = "UPDATE movie SET name=?, description=?, genre=?, runtime=?, year=?, directorID=?, userID=? WHERE movieID=?";
        PreparedStatement ps = connect().prepareStatement(query);
        ps.setString(1, movie.getName());
        ps.setString(2, movie.getDescription());
        ps.setString(3, movie.getGenre().toString());
        ps.setInt(4, movie.getRuntime());
        ps.setInt(5, movie.getYear());
        ps.setInt(6, movie.getDirector().getId());
        ps.setInt(7, movie.getUser().getId());
        ps.setInt(8, movie.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Movie movie) throws Exception {
        deleteAllActorsFromMovie(movie.getId());
        String query = "DELETE from movie WHERE movieID=" + movie.getId();
        Statement s = connect().createStatement();
        s.executeUpdate(query);
        s.close();
    }

    private void deleteAllActorsFromMovie(int id) throws Exception {
        String query = "DELETE from movie_actor WHERE movieID=" + id;
        Statement s = connect().createStatement();
        s.executeUpdate(query);
        s.close();
    }

    @Override
    public Movie get(Movie t) throws Exception {
        int id = t.getId();
        String query = "SELECT * from movie where movieID=" + id;
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        rs.next();
        
        String name = rs.getString("name");
        String description = rs.getString("description");
        Genre genre = Genre.valueOf(rs.getString("genre"));
        int runtime = rs.getInt("runtime");
        int year = rs.getInt("year");
        Director director = Controller.getInstance().getDirector(rs.getInt("directorID"));
        User user = Controller.getInstance().getUser(rs.getInt("userID"));
        ArrayList<Actor> actors = getActorsByMovie(id);
        Movie movie = new Movie(id, name, description, genre, runtime, year, director, actors, user);
        return movie;
    }

    @Override
    public ArrayList<Movie> getAll(Movie t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
