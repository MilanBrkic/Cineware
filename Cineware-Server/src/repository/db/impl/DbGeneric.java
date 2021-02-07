/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import controller.Controller;
import domain.Director;
import domain.GenericEntity;
import domain.User;
import java.util.ArrayList;
import repository.db.DbRepository;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Date;
import repository.db.DbConnectionFactory;

/**
 *
 * @author user
 */
public class DbGeneric implements DbRepository<GenericEntity> {


    
    @Override
    public ArrayList<GenericEntity> getAll(GenericEntity t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public void add(GenericEntity g) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT ")
                    .append(g.getTableName())
                    .append(" (").append(g.columnNamesForInsert()).append(")")
                    .append(" VALUES(")
                    .append(g.getInsertValues())
                    .append(")");
            String query = sb.toString();
            System.out.println(query);
            Statement s = connection.createStatement();
            s.executeUpdate(query);

            s.close();

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void update(GenericEntity g) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(g.getTableName())
                    .append(" SET ")
                    .append(g.columnNamesForUpdate())
                    .append(" WHERE ")
                    .append(g.whereCondition());
            String query = sb.toString();
            System.out.println(query);
            Statement s = connection.createStatement();
            s.executeUpdate(query);

            s.close();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void delete(GenericEntity g) throws Exception {
        //"DELETE FROM director where directorID=" + director.getId()
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ")
                    .append(g.getTableName())
                    .append(" WHERE ")
                    .append(g.whereCondition());
            String query = sb.toString();
            System.out.println(query);
            Statement s = connection.createStatement();
            s.executeUpdate(query);

            s.close();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public GenericEntity get(GenericEntity g) throws Exception {
        //Select * from director where directorID="+id;
//        Connection connection = DbConnectionFactory.getInstance().getConnection();
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT * FROM ")
//          .append(g.getTableName())
//          .append(" WHERE ")
//          .append(g.whereCondition());
//        
//        String query = sb.toString();
//        System.out.println(query);
//        Statement s = connection.createStatement();
//        ResultSet rs = s.executeQuery(query);
//        
//        s.close();
//        rs.close();
//        
//        return result;
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    

}
