/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.enums.ProductType;
import domain.enums.MeasurementUnit;
import java.math.BigDecimal;

/**
 *
 * @author user
 */
public class Product extends Article{
    private String name;
    private ProductType type;
    private User user;
    
    public Product() {
    }

    public Product(BigDecimal price, MeasurementUnit unit, String name, ProductType type, User user) {
        super(price, unit);
        this.name = name;
        this.type = type;
        this.user = user;
    }
    
    
    public Product(int id, BigDecimal price, MeasurementUnit unit, String name, ProductType type, User user) {
        super(id, price, unit);
        this.name = name;
        this.type = type;
        this.user = user;        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String getTableName() {
        return "product";
    }

    @Override
    public String columnNamesForInsert() {
        return "articleID, name, type, userID";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(id).append(", ")
          .append("'").append(name).append("', ")
          .append("'").append(type).append("', ")
          .append(user.getId());
        
        return sb.toString();
    }
    
    
    
    
    
    
}
