/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.seat;

import domain.Hall;
import domain.Seat;
import operation.AbstractGenericOperation;


/**
 *
 * @author user
 */
public class GetSeat extends AbstractGenericOperation{
    Seat result;

    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Seat)){
            throw new Exception("Invalid seat data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        Seat seat = (Seat) repo.get((Seat) params,null,null);
        Hall hall = new Hall();
        hall.setId(seat.getHall().getId());
        hall = (Hall) repo.get(hall,null,null);
        seat.setHall(hall);
        result = seat;
    }

    public Seat getResult() {
        return result;
    }
    
    
    
}
