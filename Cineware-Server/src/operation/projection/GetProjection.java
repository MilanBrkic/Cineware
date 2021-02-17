/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.projection;

import domain.Projection;
import operation.AbstractGenericOperation;
import repository.db.impl.DbProjection;

/**
 *
 * @author user
 */
public class GetProjection extends AbstractGenericOperation{

    Projection result;

    public GetProjection() {
        repo = new DbProjection();
    }
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
         if(params==null || !(params instanceof Projection)){
            throw new Exception("Invalid projection data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = ((DbProjection)repo).get((Projection)params);
    }

    public Projection getResult() {
        return result;
    }
    
    
    
}
