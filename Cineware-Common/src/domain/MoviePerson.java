/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;

/**
 *
 * @author user
 */
public abstract class MoviePerson implements GenericEntity{
    protected int id;
    protected String firstname;
    protected String lastname;
    protected Date dateOfBirth;
    protected String nationality;
    protected User user;

    public MoviePerson() {
    }
    
    public MoviePerson(String firstname, String lastname, Date dateOfBirth, String nationality, User user) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.user = user;
    }

    public MoviePerson(int id, String firstname, String lastname, Date dateOfBirth, String nationality, User user) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.user = user;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return lastname+" "+firstname;
    }

    @Override
    public abstract String getTableName();

    @Override
    public String columnNamesForInsert() {
        return "firstname, lastname, dateOfBirth, nationality, userID";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(firstname).append("', ")
          .append("'").append(lastname).append("', ")
          .append("'").append(new java.sql.Date(dateOfBirth.getTime())).append("', ")
          .append("'").append(nationality).append("', ")
          .append(user.getId());
        
        return sb.toString();
    }

    @Override
    public String columnNamesForUpdate() {
        StringBuilder sb = new StringBuilder();
        sb.append("firstname=")
          .append("'").append(firstname).append("', ")
          .append("lastname=")
          .append("'").append(lastname).append("', ")
          .append("dateOfBirth=")
          .append("'").append(new java.sql.Date(dateOfBirth.getTime())).append("', ")
          .append("nationality=")
          .append("'").append(nationality).append("', ")  
          .append("userID=")
          .append(user.getId());
        
        return sb.toString();
    }

    @Override
    public abstract String whereCondition();
    
    
}
