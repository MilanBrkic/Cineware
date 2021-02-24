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
public class UpdateWithoutPassword extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof User)){
            throw new Exception("Invalid user data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        User user = (User) params;
        StringBuilder sb = new StringBuilder();
        sb.append("firstname='").append(user.getFirstname()).append("', ")
          .append("lastname='").append(user.getLastname()).append("', ")
          .append("username='").append(user.getUsername()).append("', ")
          .append("admin=").append(user.isAdmin());
        String values = sb.toString();
        repo.update(user, values, null);
    }
    
}
