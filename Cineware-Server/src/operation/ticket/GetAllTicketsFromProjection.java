/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.ticket;

import domain.Projection;
import domain.Ticket;
import java.util.ArrayList;
import operation.AbstractGenericOperation;
import repository.db.impl.DbTicket;

/**
 *
 * @author user
 */
public class GetAllTicketsFromProjection extends AbstractGenericOperation{
    ArrayList<Ticket> result;
    
    
    public GetAllTicketsFromProjection() {
        repo = new DbTicket();
    }
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Projection)){
            throw new Exception("Invalid projection data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = ((DbTicket)repo).getAllTicketsFromProjection((Projection)params);
    }

    public ArrayList<Ticket> getResult() {
        return result;
    }
    
    
    
}
