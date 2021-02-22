/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Milan
 */
public class Actor extends MoviePerson{

    public Actor() {
        super();
    }

    public Actor(String firstname, String lastname, Date dateOfBirth, String nationality, User user) {
        super(firstname, lastname, dateOfBirth, nationality, user);
    }

    public Actor(int id, String firstname, String lastname, Date dateOfBirth, String nationality, User user) {
        super(id, firstname, lastname, dateOfBirth, nationality, user);
    }
    
    @Override
    public String whereCondition() {
        return "actorID="+id;
    }

    @Override
    public String getTableName() {
       return "actor";
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) throws Exception {
        ArrayList<GenericEntity> actors = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("actorID");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            Date date = new Date(rs.getDate("dateOfBirth").getTime());
            String nationality = rs.getString("nationality");
            User user = new User();
            user.setId(rs.getInt("userID"));
            Actor actor = new Actor(id, firstname, lastname, date, nationality, user);
            actors.add(actor);
        }
        return actors;
    }
}
