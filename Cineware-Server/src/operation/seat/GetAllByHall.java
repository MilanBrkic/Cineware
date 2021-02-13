/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.seat;

import domain.Hall;
import domain.Seat;
import java.util.ArrayList;
import operation.AbstractGenericOperation;
import operation.Getters;
import repository.db.impl.DbSeat;

/**
 *
 * @author user
 */
public class GetAllByHall extends AbstractGenericOperation implements Getters<ArrayList<Seat>>{

    ArrayList<Seat> result;

    public GetAllByHall() {
        repo = new DbSeat();
    }
    
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Hall)){
            throw new Exception("Invalid seat data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = ((DbSeat)repo).getAllByHall((Hall)params);
    }

    @Override
    public ArrayList<Seat> getResult() {
        return result;
    }
    
}
