/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.projection;

import domain.Hall;
import domain.Movie;
import domain.Projection;
import domain.User;
import java.util.ArrayList;
import operation.AbstractGenericOperation;
import repository.db.impl.DbProjection;

/**
 *
 * @author user
 */
public class GetAllProjections extends AbstractGenericOperation{

    ArrayList<Projection> result;

    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Projection)){
            throw new Exception("Invalid projection data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = repo.getAll(new Projection(), null, "startTime", null);
        for (Projection projection : result) {
            projection.setMovie((Movie) repo.get(projection.getMovie(), null,null));
            projection.setUser((User)repo.get(projection.getUser(), null,null));
            projection.setHall((Hall) repo.get(projection.getHall(), null,null));
        }
    }

    public ArrayList<Projection> getResult() {
        return result;
    }
}
