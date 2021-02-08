/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.GenericEntity;
import java.util.ArrayList;
import repository.db.DbRepository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import repository.db.DbConnectionFactory;

/**
 *
 * @author user
 */
public class DbGeneric implements DbRepository<GenericEntity> {


    
    @Override
    public ArrayList<GenericEntity> getAll(GenericEntity g) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ")
          .append(g.getTableName());
        
        String query = sb.toString();
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        
        ArrayList<GenericEntity> lista = new ArrayList<>(g.getFromResultSet(rs));
        s.close();
        rs.close();
        return lista;
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
