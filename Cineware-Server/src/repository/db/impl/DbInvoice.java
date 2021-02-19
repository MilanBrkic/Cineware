/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import controller.Controller;
import domain.Article;
import domain.Invoice;
import domain.InvoiceItem;
import domain.Product;
import domain.Ticket;
import domain.User;
import domain.enums.MeasurementUnit;
import java.math.BigDecimal;
import java.util.ArrayList;
import repository.db.DbRepository;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
/**
 *
 * @author user
 */
public class DbInvoice implements DbRepository<Invoice>{

    @Override
    public ArrayList<Invoice> getAll(Invoice t) throws Exception {
        String query = "SELECT * FROM invoice";
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        ArrayList<Invoice> invoices = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("invoiceID");
            String number = rs.getString("number");
            Date date = new Date(rs.getDate("date").getTime());
            BigDecimal total = rs.getBigDecimal("total");
            User user = Controller.getInstance().getUser(rs.getInt("userID"));
            
            
            Invoice invoice = new Invoice(id, number, date, total, user,null);
            ArrayList<InvoiceItem> items = getInvoiceItems(invoice);
            invoice.setItems(items);
            invoices.add(invoice);
        }
        
        return invoices;
    }
    
    private ArrayList<InvoiceItem> getInvoiceItems(Invoice invoice) throws Exception {
        int id = invoice.getId();
        String query = "SELECT * FROM invoice_item WHERE invoiceID="+id;
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        ArrayList<InvoiceItem> items = new ArrayList<>();
        while(rs.next()){
            int number = rs.getInt("orderNumber");
            BigDecimal price =rs.getBigDecimal("price");
            int quantity = rs.getInt("quantity");
            MeasurementUnit unit = MeasurementUnit.valueOf(rs.getString("unit"));
            BigDecimal total = rs.getBigDecimal("total");
            int articleID = rs.getInt("articleID");
            Ticket ticket = Controller.getInstance().getTicket(articleID);
            
            InvoiceItem item = new InvoiceItem(invoice, number, price, quantity, unit, null, total);
            if(ticket!=null){
               item.setArticle(ticket);
            }
            else{
                Product product = Controller.getInstance().getProduct(articleID);
                item.setArticle(product);
            }
            items.add(item);
        }
        
        return items;
    }

    @Override
    public void add(Invoice t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Invoice t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Invoice t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Invoice get(Invoice t) throws Exception {
        int id = t.getId();
        String query = "SELECT * FROM invoice WHERE invoiceID="+id;
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        
        Invoice invoice = null;
        if(rs.next()){
            String number = rs.getString("number");
            Date date = new Date(rs.getDate("date").getTime());
            BigDecimal total = rs.getBigDecimal("total");
            User user = Controller.getInstance().getUser(rs.getInt("userID"));
            
            
            invoice = new Invoice(id, number, date, total, user,null);
            ArrayList<InvoiceItem> items = getInvoiceItems(invoice);
            invoice.setItems(items);
            
        }
        
        return invoice;
    }

    
    
}
