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
    public default Connection connect() throws Exception{
        return DbConnectionFactory.getInstance().getConnection();
    }
    
    public default void disconnect()throws Exception{
        DbConnectionFactory.getInstance().getConnection().close();
    }
    
    public default void commit() throws Exception{
        DbConnectionFactory.getInstance().getConnection().commit();
    }
    
    public default void rollback() throws Exception{
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
    
}
