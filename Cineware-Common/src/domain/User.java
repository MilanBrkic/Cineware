/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author Milan
 */
public class User implements GenericEntity{
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private boolean admin;

    public User() {
    }
    
    public User(int id,String firstname, String lastname, String username, String password, boolean admin) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }
    
    public User(String firstname, String lastname, String username, String password, boolean admin) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.admin = admin;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return firstname+" "+lastname;
    }

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public String columnNamesForInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getInsertValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String columnNamesForUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String whereCondition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) throws Exception {
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("userID");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String username = rs.getString("username");
            String password = rs.getString("password");
            boolean admin = rs.getBoolean("admin");
            User user = new User(id, firstname, lastname, username, password, admin);
            users.add(user);
        }
        return new ArrayList<>(users);
    }
    
    
    
}
