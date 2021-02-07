/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.director;

import domain.Director;
import java.util.ArrayList;
import operation.AbstractGenericOperation;
import repository.db.impl.DbDirector;

/**
 *
 * @author user
 */
public class GetAllDirectors extends AbstractGenericOperation {

    private ArrayList<Director> result;
    
    public GetAllDirectors() {
        repo = new DbDirector();
        result = new ArrayList<>();
    }

    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Director)){
            throw new Exception("Invalid director data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = ((DbDirector)repo).getAll();
    }

    public ArrayList<Director> getResult() {
        return result;
    }
    
    
    
}
