/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import controller.Controller;
import domain.Director;
import domain.GenericEntity;
import domain.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import repository.db.DbRepository;

/**
 *
 * @author user
 */
public class DbDirector extends DbGeneric{

    
    public ArrayList<Director> getAll() throws Exception{
        ArrayList<Director> directors = new ArrayList<>();

        String query = "SELECT * FROM director order by lastname";
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
    public Director get(GenericEntity g) throws Exception{
        int id = ((Director)g).getId();
        String query = "Select * from director where directorID="+id;
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        
        while (rs.next()) {
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            Date date = new Date(rs.getDate("dateOfBirth").getTime());
            String nationality = rs.getString("nationality");
            User user = Controller.getInstance().getUser(rs.getInt("userID"));
            Director director = new Director(id, firstname, lastname, date, nationality, user);
            return director;
        }
        s.close();
        rs.close();
        return null;
    }
}
