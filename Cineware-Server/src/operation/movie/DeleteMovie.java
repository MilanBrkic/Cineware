/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.movie;

import domain.Actor;
import domain.Movie;
import operation.AbstractGenericOperation;
/**
 *
 * @author user
 */
public class DeleteMovie extends AbstractGenericOperation{

    
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Movie)){
            throw new Exception("Invalid movie data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        Movie movie = (Movie) params;
        repo.delete(movie,null,null);
        String table = "movie_actor";
        String where = "movieID="+movie.getId();
        repo.delete(new Actor(), table, where);
    }
    
}
