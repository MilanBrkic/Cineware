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

    

    @Override
    public void update(User user, String values, String where) throws Exception {
        updateWithoutPassword(user);
    }

    private void updateWithoutPassword(User user) throws Exception {
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

    public void updatePasswordOnly(String username, String password) throws Exception {
        String query = "UPDATE user SET password=? WHERE username=?";
        PreparedStatement ps = connect().prepareStatement(query);
        ps.setString(1, password);
        ps.setString(2, username);
        ps.executeUpdate();
        ps.close();
    }

    
    
    
    

   @Override
    public void add(User user, String table, String columns, String values) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    
    @Override
    public void delete(User user) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public User get(User t,String innerJoin,String where) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<User> getAll(User t,String where,String orderby, String innerJoin) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addWithGenKeys(User t, String table, String columns, String values) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
