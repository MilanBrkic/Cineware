/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Milan
 */
public class Director extends MoviePerson{

    public Director() {
        super();
    }

    public Director(String firstname, String lastname, Date dateOfBirth, String nationality, User user) {
        super(firstname, lastname, dateOfBirth, nationality, user);
    }

    public Director(int id, String firstname, String lastname, Date dateOfBirth, String nationality, User user) {
        super(id, firstname, lastname, dateOfBirth, nationality, user);
    }


    @Override
    public String whereCondition() {
        //WHERE directorID=?
        return "directorID="+id;
    }  

    @Override
    public String getTableName() {
        return "director";
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) throws Exception {
        ArrayList<GenericEntity> directors = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("directorID");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            Date date = new Date(rs.getDate("dateOfBirth").getTime());
            String nationality = rs.getString("nationality");
            User user = new User();
            user.setId(rs.getInt("userID"));
            Director director = new Director(id, firstname, lastname, date, nationality, user);
            directors.add(director);
        }

        return directors;
    }
    
}
