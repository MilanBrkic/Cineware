/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
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
}
