/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
/**
 *
 * @author Milan
 */
public class DbConnectionFactory {
    private static DbConnectionFactory instance;
    private Connection connection;
    
    private DbConnectionFactory() {
    }
    
    public static DbConnectionFactory getInstance(){
        if(instance==null) instance = new DbConnectionFactory();
        return instance;
    }
    
    public Connection getConnection() throws Exception{
        if(connection==null || connection.isClosed()){
            FileReader reader = new FileReader("resources/database.properties");
            Properties prop = new Properties();
            prop.load(reader);
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
        }
        return connection;
    }
    
}
