/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author user
 */
public class Invoice implements GenericEntity{
    int id;
    String number;
    Date date;
    BigDecimal total;
    ArrayList<InvoiceItem> items;
    User user;

    public Invoice() {
    }

    public Invoice(String number, Date date, BigDecimal total, User user) {
        this.number = number;
        this.date = date;
        this.total = total;
        this.items = new ArrayList<>();
        this.user = user;
    }
    
    public Invoice(int id, String number, Date date, BigDecimal total, User user) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.total = total;
        this.items = new ArrayList<>();
        this.user = user;
    }
    
    public Invoice(int id, String number, Date date, BigDecimal total, User user, ArrayList<InvoiceItem> items) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.total = total;
        this.items = items;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    
    
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ArrayList<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<InvoiceItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        final Invoice other = (Invoice) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String getTableName() {
        return "invoice";
    }

    @Override
    public String columnNamesForInsert() {
        return "invoiceID, number, date, total, userID";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(id).append(", ")
          .append("'").append(number).append("', ")
          .append("'").append(new java.sql.Date(date.getTime())).append("', ")
          .append(total).append(", ")
          .append(user.getId());
        
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

    @Override
    public void setId(int id) {
        this.id = id;
    }
    
}
