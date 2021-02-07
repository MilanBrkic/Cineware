/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.User;
import java.sql.SQLException;
import java.util.ArrayList;
import repository.db.DbRepository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Brka
 */
public class DbUser implements DbRepository<User> {

    
    public ArrayList<User> getAll() throws Exception {
        ArrayList<User> users = new ArrayList<>();

        String query = "SELECT * FROM user";
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
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
        s.close();
        rs.close();

        return users;
    }

    @Override
    public void add(User user) throws Exception {
        String query = "INSERT INTO user(firstname, lastname, username, password, admin) VALUES(?,?,?,?,?)";
        PreparedStatement ps = connect().prepareStatement(query);
        ps.setString(1, user.getFirstname());
        ps.setString(2, user.getLastname());
        ps.setString(3, user.getUsername());
        ps.setString(4, user.getPassword());
        ps.setBoolean(5, user.isAdmin());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(User user) throws Exception {
        updateWithoutPassword(user);
    }

    private void updateWithoutPassword(User user) throws SQLException {
        String query = "UPDATE user SET firstname=?, lastname=?, username=?, admin=? WHERE userID=?";
        PreparedStatement ps = connect().prepareStatement(query);
        ps.setString(1, user.getFirstname());
        ps.setString(2, user.getLastname());
        ps.setString(3, user.getUsername());
        ps.setBoolean(4, user.isAdmin());
        ps.setInt(5, user.getId());
        ps.executeUpdate();
        ps.close();
    }

    public void updatePasswordOnly(String username, String password) throws SQLException {
        String query = "UPDATE user SET password=? WHERE username=?";
        PreparedStatement ps = connect().prepareStatement(query);
        ps.setString(1, password);
        ps.setString(2, username);
        ps.executeUpdate();
        ps.close();
    }

    public boolean checkPassword(String username, String password) throws SQLException {
        String query = "SELECT * FROM user WHERE username='" + username + "'";
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        boolean rez = false;
        rs.next();
        if (rs.getString("password").equals(password)) {
            rez = true;
        }
        rs.close();
        s.close();
        return rez;
    }

    @Override
    public void delete(User user) throws Exception {
        String query = "DELETE FROM user where userID=" + user.getId();
        Statement s = connect().createStatement();
        s.executeUpdate(query);
        s.close();
    }
    
    public User getUser(int id) throws SQLException{
        String query = "SELECT * FROM user where userID="+id;
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        rs.next();
        
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String username = rs.getString("username");
        String password = rs.getString("password");
        boolean admin = rs.getBoolean("admin");
        User user = new User(id, firstname, lastname, username, password, admin);
        
        rs.close();
        s.close();
        return user;
    }

   

    @Override
    public User get(User t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<User> getAll(User t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
