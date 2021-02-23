/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.ticket;

import domain.Projection;
import domain.Seat;
import domain.Ticket;
import operation.AbstractGenericOperation;
import repository.db.impl.DbTicket;

/**
 *
 * @author user
 */
public class GetTicket extends AbstractGenericOperation {

    Ticket result;

    @Override
    protected void preconditions(Object params) throws Exception {
        if (params == null || !(params instanceof Ticket)) {
            throw new Exception("Invalid ticket data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        String innerJoin = "article a INNER JOIN ticket t ON a.articleID=t.articleID";
        result = (Ticket) repo.get((Ticket) params, innerJoin, null);
        if (result != null) {
            result.setProjection((Projection) repo.get(result.getProjection(), null, null));
            result.setSeat((Seat) repo.get(result.getSeat(), null, null));
        }

    }

    public Ticket getResult() {
        return result;
    }

}
