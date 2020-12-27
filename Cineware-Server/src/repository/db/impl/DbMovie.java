/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.Actor;
import domain.Genre;
import domain.Movie;
import java.util.ArrayList;
import repository.db.DbRepository;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author user
 */
public class DbMovie implements DbRepository<Movie>{

    @Override
    public ArrayList<Movie> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Movie movie) throws Exception {
        String query = "INSERT INTO movie(name, description, genre, runtime, year, directorID, userID) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = connect().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
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

        addActorsForMovies(id,movie.getActors());
    }
    
    private void addActorsForMovies(int id, ArrayList<Actor> actors) throws SQLException {
        String query = "INSERT INTO movie_actor(movieID,actorID) VALUES(?,?)";
        PreparedStatement ps = connect().prepareStatement(query);
        ps.setInt(1, id);
        for (Actor actor : actors) {
            ps.setInt(2, actor.getId());
            ps.executeUpdate();
        }
        ps.close();
    }

    @Override
    public void update(Movie movie) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Movie movie) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
