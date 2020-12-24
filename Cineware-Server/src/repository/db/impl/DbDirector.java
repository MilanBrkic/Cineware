/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.Director;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import repository.db.DbRepository;

/**
 *
 * @author user
 */
public class DbDirector implements DbRepository<Director>{

    @Override
    public ArrayList<Director> getAll() throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Director director) throws Exception {
        String query = "INSERT INTO director(firstname, lastname, dateOfBirth, nationality, userID) VALUES(?,?,?,?,?)";
        PreparedStatement ps = connect().prepareStatement(query);
        ps.setString(1, director.getFirstname());
        ps.setString(2, director.getLastname());
        ps.setDate(3, new java.sql.Date(director.getDateOfBirth().getTime()));
        ps.setString(4, director.getNationality());
        ps.setInt(5, director.getUser().getId());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(Director t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Director t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
