/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.ticket;

import domain.Article;
import domain.Hall;
import domain.Projection;
import domain.Seat;
import domain.Ticket;
import domain.enums.MeasurementUnit;
import java.math.BigDecimal;
import java.util.ArrayList;
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
        Projection p = (Projection) params;
        Hall hall = p.getHall();
        BigDecimal price = p.getPrice();
        MeasurementUnit unit = MeasurementUnit.PCS;
        Article article = new Article(price, unit);
        ArrayList<Seat> seats = getAllByHall(hall);
        boolean sold = false;

        for (Seat seat : seats) {
            repo.addWithGenKeys(article, null, null, null);

            Ticket ticket = new Ticket(article.getId(), price, unit, sold, p, seat);
            repo.add(ticket, null, null, null);
        }
    }
    
    private ArrayList<Seat> getAllByHall(Hall hall) throws Exception {
        ArrayList<Seat> seats = repo.getAll(new Seat(), "hallID=" + hall.getId(), null, null);
        for (Seat seat : seats) {
            seat.setHall(hall);
        }
        return seats;
    }
    
}
