/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.Article;
import java.math.BigDecimal;
import java.util.ArrayList;
import repository.db.DbRepository;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author user
 */
public class DbArticle implements DbRepository<Article>{

    @Override
    public ArrayList<Article> getAll(Article t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int addWithIndex(Article a) throws Exception{
        String query = "INSERT INTO article(price,measurementUnit) VALUES("+a.getPrice()+",'"+a.getUnit().toString()+"')";
        Statement s = connect().createStatement();
        s.execute(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = s.getGeneratedKeys();
        rs.next();
        int id = rs.getInt(1);
        s.close();
        
        return id;
    }
    
    @Override
    public void add(Article t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Article t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Article t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Article get(Article t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
