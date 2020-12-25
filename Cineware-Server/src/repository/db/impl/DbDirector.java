/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import controller.Controller;
import domain.Director;
import domain.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import repository.db.DbRepository;

/**
 *
 * @author user
 */
public class DbDirector implements DbRepository<Director>{

    @Override
    public ArrayList<Director> getAll() throws Exception{
        ArrayList<Director> directors = new ArrayList<>();

        String query = "SELECT * FROM director";
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("directorID");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            Date date = new Date(rs.getDate("dateOfBirth").getTime());
            String nationality = rs.getString("nationality");
            User user = Controller.getInstance().getUser(rs.getInt("userID"));
            Director director = new Director(id, firstname, lastname, date, nationality, user);
            directors.add(director);
        }
        s.close();
        rs.close();

        return directors;
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
    public void update(Director director) throws Exception {
        String query = "UPDATE director SET firstname=?, lastname=?, dateOfBirth=?, nationality=?, userID=? WHERE directorID=?";
        
        PreparedStatement ps = connect().prepareStatement(query);
        ps.setString(1, director.getFirstname());
        ps.setString(2, director.getLastname());
        ps.setDate(3, new java.sql.Date(director.getDateOfBirth().getTime()));
        ps.setString(4, director.getNationality());
        ps.setInt(5, director.getUser().getId());
        ps.setInt(6, director.getId());
        
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(Director director) throws Exception {
        String query = "DELETE FROM director where directorID=" + director.getId();
        Statement s = connect().createStatement();
        s.executeUpdate(query);
        s.close();
    }
    
}
