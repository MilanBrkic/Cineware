/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.projection;

import domain.Projection;
import operation.AbstractGenericOperation;
import operation.ticket.AddTickets;
import repository.db.impl.DbGeneric;
import repository.db.impl.DbProjection;

/**
 *
 * @author user
 */
public class AddProjection extends AbstractGenericOperation{

    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Projection)){
            throw new Exception("Invalid projection data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        try {
            Projection projection = (Projection) params;
            AbstractGenericOperation ago = new IsHallOccupied();
            ago.execute(projection);
            if (!((IsHallOccupied)ago).getResult()) {
                throw new Exception("Hall ocuppied in given time");
            }


             ((DbGeneric)repo).addWithGenKeys(projection);
             
            AbstractGenericOperation ago2 = new AddTickets();
            ago2.executeWithoutCommit(projection);
        } catch (Exception e) {
            throw e;
        }
    }
    
}
