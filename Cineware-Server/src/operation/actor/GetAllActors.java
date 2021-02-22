/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.actor;

import domain.Actor;
import domain.User;
import java.util.ArrayList;
import operation.AbstractGenericOperation;

/**
 *
 * @author user
 */
public class GetAllActors extends AbstractGenericOperation{
    private ArrayList<Actor> result;
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Actor)){
            throw new Exception("Invalid director data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = repo.getAll(new Actor(), null, "lastname", null);
        for (Actor actor : result) {
            actor.setUser((User) repo.get(actor.getUser(),null,null));
        }
        
    }

    public ArrayList<Actor> getResult() {
        return result;
    }
}
