/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.projection;

import domain.Projection;
import java.util.ArrayList;
import operation.AbstractGenericOperation;
import repository.db.impl.DbProjection;

/**
 *
 * @author user
 */
public class GetAllProjections extends AbstractGenericOperation{

    ArrayList<Projection> result;

    public GetAllProjections() {
        repo = new DbProjection();
    }
    
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Projection)){
            throw new Exception("Invalid movie data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = ((DbProjection)repo).getAll();
    }

    public ArrayList<Projection> getResult() {
        return result;
    }
 
    
    
}
