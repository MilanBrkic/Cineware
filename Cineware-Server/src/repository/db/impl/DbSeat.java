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
public class DbSeat {

    
    
    public ArrayList<Seat> getAll(Seat t,String where,String orderby, String innerJoin) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void add(Seat t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(Seat t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Seat t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public Seat get(Seat seat) throws Exception {
//        int id = seat.getId();
//        String query = "SELECT * from seat WHERE seatID="+id;
//        Statement s = connect().createStatement();
//        ResultSet rs = s.executeQuery(query);
//        
//        if(rs.next()){
//            int number = rs.getInt("number");
//            char row = rs.getString("row").charAt(0);
//            Hall hall = Controller.getInstance().getHall(rs.getInt("hallID"));
//            
//            Seat seatara = new Seat(id, hall, number, row);
//            return seatara;
//        }
//        return null;
//    }
    
}
