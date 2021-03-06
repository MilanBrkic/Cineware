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
    public ArrayList<GenericEntity> getAll(GenericEntity g, String where, String orderby, String innerJoin) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");

        if (innerJoin != null) {
            sb.append(innerJoin);
        } else {
            sb.append(g.getTableName());
        }

        if (where != null) {
            sb.append(" WHERE ")
                    .append(where);
        }
        if (orderby != null) {
            sb.append(" ORDER BY ")
                    .append(orderby);
        }

        String query = sb.toString();
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);

        ArrayList<GenericEntity> lista = new ArrayList<>(g.getFromResultSet(rs));
        s.close();
        rs.close();
        return lista;
    }

    @Override
    public void add(GenericEntity g, String table, String columns, String values) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ");
                    if(table!=null) sb.append(table);
                    else sb.append(g.getTableName());
                    
                    sb.append("(");
                    if(columns!=null) sb.append(columns);
                    else sb.append(g.columnNamesForInsert());
                    sb.append(")");
                    
                    sb.append(" VALUES(");
                    if(values!=null) sb.append(values);
                    else sb.append(g.getInsertValues());
                    sb.append(")");
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
    public void addWithGenKeys(GenericEntity g, String table, String columns, String values) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ");
                    if(table!=null) sb.append(table);
                    else sb.append(g.getTableName());
                    
                    sb.append("(");
                    if(columns!=null) sb.append(columns);
                    else sb.append(g.columnNamesForInsert());
                    sb.append(")");
                    
                    sb.append(" VALUES(");
                    if(values!=null) sb.append(values);
                    else sb.append(g.getInsertValues());
                    sb.append(")");
            String query = sb.toString();
            System.out.println(query);
            Statement s = connection.createStatement();
            s.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = s.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            g.setId(id);
            s.close();

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void update(GenericEntity g, String values, String where) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(g.getTableName())
                    .append(" SET ");
                    if(values!=null) sb.append(values);
                    else sb.append(g.columnNamesForUpdate());
                    sb.append(" WHERE ");
                    
                    if(where!=null) sb.append(where);
                    else sb.append(g.whereCondition());
                    
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
    public void delete(GenericEntity g,String table, String where) throws Exception {
        //"DELETE FROM director where directorID=" + director.getId()
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ");
                    if(table!=null) sb.append(table);
                    else sb.append(g.getTableName());
                    sb.append(" WHERE ");
                    if(where!=null) sb.append(where);
                    else sb.append(g.whereCondition());
                    
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
    public GenericEntity get(GenericEntity g, String innerJoin, String where) throws Exception {
        //SELECT * FROM user where userID="+id
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");

        if (innerJoin != null) {
            sb.append(innerJoin);
        } else {
            sb.append(g.getTableName());
        }
        
        sb.append(" WHERE ");
        
        if (where != null) {
            sb.append(where);
        } else {
            sb.append(g.whereCondition());
        }

        String query = sb.toString();
        System.out.println(query);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);

        ArrayList<GenericEntity> lista = new ArrayList<>(g.getFromResultSet(rs));
        s.close();
        rs.close();
        if(lista.size()==0) return null;
        return lista.get(0);
    }

}
