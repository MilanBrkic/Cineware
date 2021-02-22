/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.hall;

import domain.Hall;
import java.util.ArrayList;
import operation.AbstractGenericOperation;

/**
 *
 * @author user
 */
public class GetAllHalls extends AbstractGenericOperation{

    ArrayList<Hall> result;
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Hall)){
            throw new Exception("Invalid hall data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = repo.getAll((Hall)params, null,null, null);
    }

    public ArrayList<Hall> getResult() {
        return result;
    }
    
    
    
}
