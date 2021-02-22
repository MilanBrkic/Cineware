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


    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Hall)){
            throw new Exception("Invalid hall data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        Hall hall = (Hall) params;
        result = repo.getAll(new Seat(), "hallID="+hall.getId(),null, null);
        for (Seat seat : result) {
            seat.setHall(hall);
        }
    }

    @Override
    public ArrayList<Seat> getResult() {
        return result;
    }
    
}
