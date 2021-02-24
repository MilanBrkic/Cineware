/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.projection;

import domain.Projection;
import operation.AbstractGenericOperation;

/**
 *
 * @author user
 */
public class DeleteProjection extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Projection)){
            throw new Exception("Invalid projection data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        repo.delete((Projection)params,null,null);
    }
    
}
