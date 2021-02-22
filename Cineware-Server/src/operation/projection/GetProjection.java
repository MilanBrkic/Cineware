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
import operation.AbstractGenericOperation;
import repository.db.impl.DbProjection;

/**
 *
 * @author user
 */
public class GetProjection extends AbstractGenericOperation {

    Projection result;

    @Override
    protected void preconditions(Object params) throws Exception {
        if (params == null || !(params instanceof Projection)) {
            throw new Exception("Invalid projection data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = (Projection) repo.get((Projection) params, null,null);
        result.setMovie((Movie) repo.get(result.getMovie(), null,null));
        result.setUser((User) repo.get(result.getUser(), null,null));
        result.setHall((Hall) repo.get(result.getHall(), null,null));
    }

    public Projection getResult() {
        return result;
    }

}
