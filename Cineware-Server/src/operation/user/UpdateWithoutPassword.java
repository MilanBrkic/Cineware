/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.user;

import domain.User;
import operation.AbstractGenericOperation;
import repository.db.impl.DbUser;

/**
 *
 * @author user
 */
public class UpdateWithoutPassword extends AbstractGenericOperation{

    public UpdateWithoutPassword() {
        repo = new DbUser();
    }
    
    

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof User)){
            throw new Exception("Invalid user data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        ((DbUser)repo).update((User)params, null, null);
    }
    
}
