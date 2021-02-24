/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.user;

import domain.User;
import operation.AbstractGenericOperation;

/**
 *
 * @author user
 */
public class UpdatePasswordOnly extends AbstractGenericOperation{

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
        String where = "username='"+username+"'";
        String values = "password='"+password+"'";
       
        repo.update(user, values, where);
    }
}
