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
import operation.AbstractGenericOperation;
import operation.Getters;
import repository.db.impl.DbMovie;

/**
 *
 * @author user
 */
public class GetMovie extends AbstractGenericOperation implements Getters<Movie> {

    Movie result;

    @Override
    protected void preconditions(Object params) throws Exception {
        if (params == null || !(params instanceof Movie)) {
            throw new Exception("Invalid movie data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = (Movie) repo.get((Movie) params, null,null);
        result.setUser((User) repo.get(result.getUser(), null,null));
        result.setDirector((Director) repo.get(result.getDirector(), null,null));
        String innerJoin = "movie_actor ma INNER JOIN actor a ON a.actorID=ma.actorID";
        result.setActors(repo.getAll(new Actor(), "movieID=" + result.getId(), null, innerJoin));
    }

    @Override
    public Movie getResult() {
        return result;
    }

}
