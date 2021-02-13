/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import controller.Controller;
import domain.Article;
import domain.Hall;
import domain.enums.MeasurementUnit;
import domain.Projection;
import domain.Seat;
import domain.Ticket;
import java.math.BigDecimal;
import java.util.ArrayList;
import repository.db.DbRepository;

/**
 *
 * @author user
 */
public class DbTicket implements DbRepository<Ticket>{

    
    public void addTickets(Projection p) throws Exception{
        Hall hall = p.getHall();
        BigDecimal price = p.getPrice();
        MeasurementUnit unit = MeasurementUnit.PCS;
        Article article = new Article(price, unit);
        ArrayList<Seat> seats = Controller.getInstance().getAllByHall(hall);
        for (Seat seat : seats) {
            int id = Controller.getInstance().addArticle(article);
            boolean sold = false;
            
            Ticket ticket = new Ticket(id, price, unit, sold, p, seat);
            Controller.getInstance().addTicket(ticket);
        }
    }
    
    
    
    @Override
    public ArrayList<Ticket> getAll(Ticket t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Ticket t) throws Exception {
       
    }

    @Override
    public void update(Ticket t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Ticket t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ticket get(Ticket t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
