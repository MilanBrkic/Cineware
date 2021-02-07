/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.director;

import domain.Director;
import java.util.ArrayList;
import operation.AbstractGenericOperation;
import operation.Getters;
import repository.db.impl.DbDirector;

/**
 *
 * @author user
 */
public class GetDirector extends AbstractGenericOperation implements Getters<Director>{

    private Director result;

    public GetDirector() {
        repo = new DbDirector();
    }
    
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Director)){
            throw new Exception("Invalid director data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = ((DbDirector)repo).get((Director) params);
    }

    @Override
    public Director getResult() {
        return result;
    }
    
    
    
}
