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
import java.sql.Statement;
import java.sql.ResultSet;
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
    
    
    public ArrayList<Ticket> getAllTicketsFromProjection(Projection projection) throws Exception {
        String query = "SELECT  a.articleID, a.price, a.measurementUnit, t.sold, t.seatID " +
                       " FROM article a " +
                       " INNER JOIN ticket t " +
                       " ON a.articleID=t.articleID " +
                       " WHERE projectionID="+projection.getId();
        ArrayList<Ticket> tickets = new ArrayList<>();
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        while(rs.next()){
            int id = rs.getInt("articleID");
            BigDecimal price = rs.getBigDecimal("price");
            MeasurementUnit unit = MeasurementUnit.PCS;
            boolean sold = rs.getBoolean("sold");
            
            Seat seat = Controller.getInstance().getSeat(rs.getInt("seatId"));
            
            Ticket ticket = new Ticket(id, price, unit, sold, projection, seat);
            tickets.add(ticket);
        }
        
        s.close();
        rs.close();
        return tickets;
    }
    
    public void setTicketToSold(Ticket ticket) throws Exception{
        String query = "UPDATE ticket SET sold=true WHERE articleID="+ticket.getId();
        System.out.println(query);
        Statement s = connect().createStatement();
        s.executeUpdate(query);
        s.close();
    }
    
    public void setTicketToNotSold(Ticket ticket) throws Exception{
        String query = "UPDATE ticket SET sold=false WHERE articleID="+ticket.getId();
        System.out.println(query);
        Statement s = connect().createStatement();
        s.executeUpdate(query);
        s.close();
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
        int id = t.getId();
        String query = "SELECT  a.articleID, a.price, t.sold, t.seatID, t.projectionID " +
                       " FROM article a " +
                       " INNER JOIN ticket t " +
                       " ON a.articleID=t.articleID " +
                       " WHERE t.articleID="+id;
        
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        Ticket ticket = null;
        if(rs.next()){
            BigDecimal price = rs.getBigDecimal("price");
            MeasurementUnit unit = MeasurementUnit.PCS;
            boolean sold = rs.getBoolean("sold");
            
            Seat seat = Controller.getInstance().getSeat(rs.getInt("seatID"));
            
            Projection projection = Controller.getInstance().getProjection(rs.getInt("projectionID"));
            ticket = new Ticket(id, price, unit, sold, projection, seat);
        }
        
        s.close();
        rs.close();
        return ticket;
    }
    
}
