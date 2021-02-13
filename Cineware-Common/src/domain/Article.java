/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;

/**
 *
 * @author user
 */
public class Article {
    private int id;
    private BigDecimal price;
    private MeasurementUnit unit;

    public Article() {
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
    
    
}
