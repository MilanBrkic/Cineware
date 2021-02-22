/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.movie;

import domain.Actor;
import domain.Director;
import domain.Movie;
import domain.User;
import java.util.ArrayList;
import operation.AbstractGenericOperation;
import repository.db.impl.DbMovie;

/**
 *
 * @author user
 */
public class GetAllMovies extends AbstractGenericOperation{
    ArrayList<Movie> result;

    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Movie)){
            throw new Exception("Invalid movie data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = repo.getAll(new Movie(), null, "name", null);
        for (Movie movie : result) {
            movie.setUser((User) repo.get(movie.getUser(),null,null));
            movie.setDirector((Director) repo.get(movie.getDirector(),null,null));
            String innerJoin = "movie_actor ma INNER JOIN actor a ON a.actorID=ma.actorID";
            movie.setActors(repo.getAll(new Actor(), "movieID="+movie.getId(), null, innerJoin));
        }
    }

    public ArrayList<Movie> getResult() {
        return result;
    }
    
    
    
}
