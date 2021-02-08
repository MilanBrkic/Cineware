/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation;

import domain.User;

/**
 *
 * @author user
 */
public class GenericGet<T> extends AbstractGenericOperation implements Getters<T>{
    T result;
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null){
            throw new Exception("Invalid director data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = (T) repo.get((T)params);
    }

    @Override
    public T getResult() {
        return result;
    }
    
}
