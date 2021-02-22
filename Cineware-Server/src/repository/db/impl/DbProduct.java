/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import controller.Controller;
import domain.Product;
import domain.User;
import domain.enums.MeasurementUnit;
import domain.enums.ProductType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import operation.AbstractGenericOperation;
import operation.GenericGet;
import repository.db.DbRepository;

/**
 *
 * @author user
 */
public class DbProduct implements DbRepository<Product>{
    
    
    
    @Override
    public ArrayList<Product> getAll(Product p,String where,String orderby, String innerJoin) throws Exception {
        String query = "SELECT a.articleID, a.price, a.measurementUnit, p.name, p.type, p.userID " +
                       "FROM article a " +
                       "INNER JOIN product p " +
                       "ON a.articleID=p.articleID";
        
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        
        ArrayList<Product> products = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("articleID");
            BigDecimal price = rs.getBigDecimal("price");
            MeasurementUnit unit = MeasurementUnit.valueOf(rs.getString("measurementUnit"));
            String name = rs.getString("name");
            ProductType type = ProductType.valueOf(rs.getString("type"));

            int userId =  rs.getInt("userID");
            
      
            User user = Controller.getInstance().getUser(userId);
            
            Product product = new Product(id, price, unit, name, type, user);
            products.add(product);
        }
        
        return products;
    }
    
    @Override
    public Product get(Product p,String innerJoin,String where) throws Exception {
        int id = p.getId();
        String query = "SELECT a.articleID, a.price, a.measurementUnit, p.name, p.type, p.userID " +
                       "FROM article a " +
                       "INNER JOIN product p " +
                       "ON a.articleID=p.articleID"+
                       " WHERE p.articleID="+id;
        
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        Product product = null;
        if(rs.next()){
            BigDecimal price = rs.getBigDecimal("price");
            MeasurementUnit unit = MeasurementUnit.valueOf(rs.getString("measurementUnit"));
            String name = rs.getString("name");
            ProductType type = ProductType.valueOf(rs.getString("type"));
            int userId =  rs.getInt("userID");
            
            User user = Controller.getInstance().getUser(userId);
            
            product = new Product(id, price, unit, name, type, user);
        }
        
        rs.close();
        s.close();
        return product;
    }

    @Override
    public void add(Product t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Product t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Product t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
