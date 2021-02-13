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
public class Product extends Article{
    private String name;
    private ProductType type;

    public Product() {
    }

    public Product(int id, BigDecimal price, MeasurementUnit unit, String name, ProductType type) {
        super(id, price, unit);
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
    
    
}
