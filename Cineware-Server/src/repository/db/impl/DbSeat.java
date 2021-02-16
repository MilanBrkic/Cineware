/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import controller.Controller;
import domain.Hall;
import domain.Seat;
import java.util.ArrayList;
import repository.db.DbRepository;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author user
 */
public class DbSeat implements DbRepository<Seat>{

    public ArrayList<Seat> getAllByHall(Hall hall) throws Exception{
        String query = "SELECT * from seat where hallID="+hall.getId();
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        
        ArrayList<Seat>  seats = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("seatID");
            int number = rs.getInt("number");
            char row  = rs.getString("row").charAt(0);
            
            Seat seat = new Seat(id, hall, number, row);
            seats.add(seat);
        }
        
        return seats;
    }
    
    @Override
    public ArrayList<Seat> getAll(Seat t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Seat t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Seat t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Seat t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Seat get(Seat seat) throws Exception {
        int id = seat.getId();
        String query = "SELECT * from seat WHERE seatID="+id;
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        
        if(rs.next()){
            int number = rs.getInt("number");
            char row = rs.getString("row").charAt(0);
            Hall hall = Controller.getInstance().getHall(rs.getInt("hallID"));
            
            Seat seatara = new Seat(id, hall, number, row);
            return seatara;
        }
        return null;
    }
    
}
