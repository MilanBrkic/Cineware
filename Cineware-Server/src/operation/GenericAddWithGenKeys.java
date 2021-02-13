/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation;

import domain.GenericEntity;
import repository.db.impl.DbGeneric;

/**
 *
 * @author user
 */
public class GenericAddWithGenKeys<T> extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null){
            throw new Exception("Invalid param data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        ((DbGeneric)repo).addWithGenKeys((GenericEntity)params);
    }
    
}
