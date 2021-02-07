/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.Hall;
import domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.db.DbRepository;

/**
 *
 * @author Milan
 */
public class DbHall implements DbRepository<Hall> {

    @Override
    public ArrayList<Hall> getAll() throws Exception {
        ArrayList<Hall> halls = new ArrayList<>();

        String query = "SELECT * FROM hall";
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("hallID");
            String hallName = rs.getString("hallName");
            int number = rs.getInt("numberOfSeats");
            Hall hall = new Hall(id, hallName, number);
            halls.add(hall);
        }
        s.close();
        rs.close();

        return halls;
    }

    @Override
    public void add(Hall t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Hall t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Hall t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hall get(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
