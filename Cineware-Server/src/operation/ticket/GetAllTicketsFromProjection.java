/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.ticket;

import domain.Projection;
import domain.Seat;
import domain.Ticket;
import java.util.ArrayList;
import operation.AbstractGenericOperation;

/**
 *
 * @author user
 */
public class GetAllTicketsFromProjection extends AbstractGenericOperation{
    ArrayList<Ticket> result;
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Projection)){
            throw new Exception("Invalid projection data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        Projection projection = (Projection) params;
        String innerJoin = "article a INNER JOIN ticket t ON a.articleID=t.articleID";
        String where = "t.projectionID="+projection.getId();
        result = repo.getAll(new Ticket(), where, null, innerJoin);
        for (Ticket ticket : result) {
            ticket.setProjection(projection);
            ticket.setSeat((Seat) repo.get(ticket.getSeat(), null, null));
        }
    }

    public ArrayList<Ticket> getResult() {
        return result;
    }    
}
