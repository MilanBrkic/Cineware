/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class GenericGetAll<T> extends AbstractGenericOperation{
    private ArrayList<T> result;
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null){
            throw new Exception("Invalid director data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = repo.getAll((T)params);
    }

    public ArrayList<T> getResult() {
        return result;
    }
    
    
    
}
