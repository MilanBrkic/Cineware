/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.Seat;
import domain.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import repository.db.DbRepository;

/**
 *
 * @author Milan
 */
public class DbSeat implements DbRepository<Seat>{

    @Override
    public ArrayList<Seat> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Seat seat) throws Exception {
        String query = "INSERT INTO seat(hallID, number, row) VALUES(?,?,?)";
        PreparedStatement ps = connect().prepareStatement(query);
        ps.setInt(1, seat.getHall().getId());
        ps.setInt(2, seat.getNumber());
        ps.setString(3, String.valueOf(seat.getRow()));
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(Seat t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Seat t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
