/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.director;

import domain.Director;
import domain.User;
import java.util.ArrayList;
import operation.AbstractGenericOperation;

/**
 *
 * @author user
 */
public class GetAllDirectors extends AbstractGenericOperation {

    private ArrayList<Director> result;
    

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Director)){
            throw new Exception("Invalid director data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = repo.getAll(new Director(), null, "lastname", null);
        for (Director director : result) {
            director.setUser((User) repo.get(director.getUser(),null ,null));
        }
    }

    public ArrayList<Director> getResult() {
        return result;
    }
}
