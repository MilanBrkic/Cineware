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
public class SetTicketToSold extends AbstractGenericOperation{

    public SetTicketToSold() {
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
        Ticket ticket = (Ticket) params;
        repo.update(ticket, "sold= not sold", "articleID="+ticket.getId());
    }
    
}
