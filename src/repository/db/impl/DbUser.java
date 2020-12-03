/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domen.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.db.DbRepository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author Brka
 */
public class DbUser implements DbRepository<User>{

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();

        try {
            String query = "SELECT * FROM user";
            Statement s = connect().createStatement();
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("userID");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean admin = rs.getBoolean("admin");
                User user = new User(id,firstname, lastname, username, password, admin);
                users.add(user);
            }
            s.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbUser.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
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
        try {
            commit();
        } catch (SQLException e) {
            rollback();
            throw new Exception("User could not be saved!");
        }
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
        try {
            connect().commit();
        } catch (Exception e) {
            connect().rollback();
            throw e;
        }
    }
    
    @Override
    public void delete(User t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
