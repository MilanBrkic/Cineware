/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.movie;

import domain.Movie;
import operation.AbstractGenericOperation;
import repository.Repository;
import repository.db.impl.DbMovie;

/**
 *
 * @author user
 */
public class AddMovie extends AbstractGenericOperation{

    public AddMovie() {
        repo = new DbMovie();
    }
    
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Movie)){
            throw new Exception("Invalid movie data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        ((DbMovie)repo).add((Movie) params);
    }

    
    
}
