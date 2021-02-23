/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.movie;

import domain.Actor;
import domain.Movie;
import operation.AbstractGenericOperation;
import repository.Repository;
import repository.db.impl.DbMovie;

/**
 *
 * @author user
 */
public class AddMovie extends AbstractGenericOperation{

    
    
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Movie)){
            throw new Exception("Invalid movie data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        Movie movie = (Movie) params;
        repo.addWithGenKeys(movie,null,null,null);
        for (Actor actor : movie.getActors()) {
            String values = movie.getId()+", "+actor.getId();
            repo.add(actor, "movie_actor", "movieID,actorID", values);
        }
        
    }

    
    
}
