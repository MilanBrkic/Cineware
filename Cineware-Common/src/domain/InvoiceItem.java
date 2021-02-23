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
import java.util.Objects;

/**
 *
 * @author user
 */
public class InvoiceItem implements GenericEntity{
    int InvoiceId;
    transient Invoice invoice;
    int orderNumber;
    BigDecimal price;
    int quantity;
    MeasurementUnit unit;
    Article article;
    BigDecimal total;

    public InvoiceItem() {
    }

    public InvoiceItem(Invoice invoice, int orderNumber, BigDecimal price, int quantity, MeasurementUnit unit, Article article, BigDecimal total) {
        this.invoice = invoice;
        this.orderNumber = orderNumber;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.article = article;
        this.total = total;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }


    public MeasurementUnit getUnit() {
        return unit;
    }

    public void setUnit(MeasurementUnit unit) {
        this.unit = unit;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
        final InvoiceItem other = (InvoiceItem) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.invoice, other.invoice)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String getTableName() {
        return "invoice_item";
    }

    @Override
    public String columnNamesForInsert() {
        return "invoiceID, orderNumber, price, quantity, unit, articleID, total";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(InvoiceId).append(", ")
          .append(orderNumber).append(", ")
          .append(price).append(", ")
          .append(quantity).append(", ")
          .append("'").append(unit).append("', ")
          .append(article.getId()).append(", ")
          .append(total);
        
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
        ArrayList<GenericEntity> items = new ArrayList<>();
        while(rs.next()){
            int number = rs.getInt("orderNumber");
            BigDecimal price =rs.getBigDecimal("price");
            int quantity = rs.getInt("quantity");
            MeasurementUnit unit = MeasurementUnit.valueOf(rs.getString("unit"));
            BigDecimal total = rs.getBigDecimal("total");
            int articleID = rs.getInt("articleID");
            Ticket ticket = new Ticket();
            ticket.setId(articleID);
            
            InvoiceItem item = new InvoiceItem(invoice, number, price, quantity, unit, ticket, total);
            
            items.add(item);
        }
        
        return items;
    }

    @Override
    public void setId(int id) {
        InvoiceId = id;
    }
    
}
