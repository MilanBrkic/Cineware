/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.ticket;

import domain.Ticket;
import operation.AbstractGenericOperation;
import repository.db.impl.DbTicket;

/**
 *
 * @author user
 */
public class GetTicket extends AbstractGenericOperation{

    Ticket result;

    public GetTicket() {
        repo = new DbTicket();
    }
    
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Ticket)){
            throw new Exception("Invalid ticket data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = ((DbTicket)repo).get((Ticket)params);
    }

    public Ticket getResult() {
        return result;
    }
    
    
    
}
