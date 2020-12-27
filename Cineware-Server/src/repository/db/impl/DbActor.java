/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import controller.Controller;
import domain.Actor;
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
public class DbActor implements DbRepository<Actor>{

    @Override
    public ArrayList<Actor> getAll() throws Exception{
        ArrayList<Actor> actors = new ArrayList<>();

        String query = "SELECT * FROM actor order by lastname";
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("actorID");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            Date date = new Date(rs.getDate("dateOfBirth").getTime());
            String nationality = rs.getString("nationality");
            User user = Controller.getInstance().getUser(rs.getInt("userID"));
            Actor actor = new Actor(id, firstname, lastname, date, nationality, user);
            actors.add(actor);
        }
        s.close();
        rs.close();

        return actors;
    }

    @Override
    public void add(Actor actor) throws Exception {
        String query = "INSERT INTO actor(firstname, lastname, dateOfBirth, nationality, userID) VALUES(?,?,?,?,?)";
        PreparedStatement ps = connect().prepareStatement(query);
        ps.setString(1, actor.getFirstname());
        ps.setString(2, actor.getLastname());
        ps.setDate(3, new java.sql.Date(actor.getDateOfBirth().getTime()));
        ps.setString(4, actor.getNationality());
        ps.setInt(5, actor.getUser().getId());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(Actor actor) throws Exception {
        String query = "UPDATE actor SET firstname=?, lastname=?, dateOfBirth=?, nationality=?, userID=? WHERE actorID=?";
        
        PreparedStatement ps = connect().prepareStatement(query);
        ps.setString(1, actor.getFirstname());
        ps.setString(2, actor.getLastname());
        ps.setDate(3, new java.sql.Date(actor.getDateOfBirth().getTime()));
        ps.setString(4, actor.getNationality());
        ps.setInt(5, actor.getUser().getId());
        ps.setInt(6, actor.getId());
        
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(Actor actor) throws Exception {
        String query = "DELETE FROM actor where actorID=" + actor.getId();
        Statement s = connect().createStatement();
        s.executeUpdate(query);
        s.close();
    }
    
}
