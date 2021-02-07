/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.director;

import domain.Director;
import operation.AbstractGenericOperation;

/**
 *
 * @author user
 */
public class AddDirector extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Director)){
            throw new Exception("Invalid director data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception{
        repo.add((Director)params);
    }
    
}
