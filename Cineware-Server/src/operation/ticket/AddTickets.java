/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.ticket;

import domain.Projection;
import domain.Ticket;
import operation.AbstractGenericOperation;
import repository.db.impl.DbTicket;

/**
 *
 * @author user
 */
public class AddTickets extends AbstractGenericOperation{

    public AddTickets() {
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
        ((DbTicket)repo).addTickets((Projection)params);
    }
    
}
