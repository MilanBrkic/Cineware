/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.actor;

import domain.Actor;
import java.util.ArrayList;
import operation.AbstractGenericOperation;
import repository.db.impl.DbActor;

/**
 *
 * @author user
 */
public class GetAllActors extends AbstractGenericOperation{
    private ArrayList<Actor> result;
    
    public GetAllActors() {
        repo = new DbActor();
        result = new ArrayList<>();
    }

    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Actor)){
            throw new Exception("Invalid director data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = ((DbActor)repo).getAll();
    }

    public ArrayList<Actor> getResult() {
        return result;
    }
}
