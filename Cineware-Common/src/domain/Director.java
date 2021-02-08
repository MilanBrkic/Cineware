/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
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
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
