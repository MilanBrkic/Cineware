/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db;

import repository.Repository;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author Milan
 */
public interface DbRepository<T> extends Repository<T>{
    public default Connection connect() throws SQLException{
        return DbConnectionFactory.getInstance().getConnection();
    }
    
    public default void disconnect()throws SQLException{
        DbConnectionFactory.getInstance().getConnection().close();
    }
    
    public default void commit() throws SQLException{
        DbConnectionFactory.getInstance().getConnection().commit();
    }
    
    public default void rollback() throws SQLException{
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
    
}
