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
public class CheckPassword extends AbstractGenericOperation{

    private boolean result;

    

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof User)){
            throw new Exception("Invalid user data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        User user = (User) params;
        String username = user.getUsername();
        String password = user.getPassword();
        User user1 = (User) repo.get(new User(),null ,"username='"+username+"'");
        if(user.getPassword().equals(password)){
            result = true;
        }
        else result = false;
    }
    
    
   public boolean getResult(){
       return result;
   }
    
    
}
