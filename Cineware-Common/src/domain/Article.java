/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.enums.MeasurementUnit;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Article implements GenericEntity{
    protected int id;
    protected BigDecimal price;
    protected MeasurementUnit unit;

    public Article() {
    }
    
    public Article(BigDecimal price, MeasurementUnit unit) {
        this.price = price;
        this.unit = unit;
    }
    
    public Article(int id, BigDecimal price, MeasurementUnit unit) {
        this.id = id;
        this.price = price;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public MeasurementUnit getUnit() {
        return unit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setUnit(MeasurementUnit unit) {
        this.unit = unit;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Article other = (Article) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "article";
    }

    @Override
    public String columnNamesForInsert() {
        return "price,measurementUnit";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(price).append(", ")
          .append("'").append(unit).append("'");
        
        return sb.toString();
    }

    @Override
    public String columnNamesForUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String whereCondition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
