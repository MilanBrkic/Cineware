/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.seat;

import domain.Seat;
import operation.AbstractGenericOperation;
import repository.db.impl.DbSeat;


/**
 *
 * @author user
 */
public class GetSeat extends AbstractGenericOperation{
    Seat result;

    public GetSeat() {
        repo = new DbSeat();
    }
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Seat)){
            throw new Exception("Invalid seat data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = (Seat) repo.get((Seat) params);
    }

    public Seat getResult() {
        return result;
    }
    
    
    
}
