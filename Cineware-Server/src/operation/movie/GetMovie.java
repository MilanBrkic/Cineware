/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.movie;

import domain.Director;
import domain.Movie;
import operation.AbstractGenericOperation;
import operation.Getters;
import repository.db.impl.DbMovie;

/**
 *
 * @author user
 */
public class GetMovie extends AbstractGenericOperation implements Getters<Movie> {

    Movie result;

    public GetMovie() {
        repo = new DbMovie();
    }

    @Override
    protected void preconditions(Object params) throws Exception {
        if (params == null || !(params instanceof Movie)) {
            throw new Exception("Invalid director data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = (Movie) repo.get((Movie) params);
    }

    @Override
    public Movie getResult() {
        return result;
    }

}
