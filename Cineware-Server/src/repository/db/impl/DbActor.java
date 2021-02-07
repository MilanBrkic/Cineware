/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import controller.Controller;
import domain.Actor;
import domain.User;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author user
 */
public class DbActor extends DbGeneric{

   
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

}
