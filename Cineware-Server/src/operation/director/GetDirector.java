/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.director;

import domain.Director;
import domain.User;
import operation.AbstractGenericOperation;
import operation.Getters;

/**
 *
 * @author user
 */
public class GetDirector extends AbstractGenericOperation implements Getters<Director>{

    private Director result;

    
    
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Director)){
            throw new Exception("Invalid director data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = (Director) repo.get((Director)params, null,null);
        result.setUser((User) repo.get(result.getUser(),null,null));
    }

    @Override
    public Director getResult() {
        return result;
    }
    
    
    
}
