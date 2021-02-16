/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model.table;

import communcation.Communcation;
import domain.Invoice;
import domain.InvoiceItem;
import domain.Projection;
import domain.Seat;
import domain.Ticket;
import domain.enums.MeasurementUnit;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class InvoiceTableModel extends AbstractTableModel {

    Invoice invoice;
    String[] columnNames = {"Order No.", "Article", "Unit", "Price", "Quantity", "Total"};
    Map<Projection, ArrayList<Ticket>> map;

    public InvoiceTableModel(Invoice invoice) {
        this.invoice = invoice;
        this.invoice.setItems(new ArrayList<>());
        this.invoice.setTotal(BigDecimal.ZERO);
        map = new HashMap<>();
    }

    @Override
    public int getRowCount() {
        return invoice.getItems().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceItem item = invoice.getItems().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getOrderNumber();
            case 1:
                return item.getArticle().toString();
            case 2:
                return item.getUnit();
            case 3:
                return item.getPrice();
            case 4:
                return item.getQuantity();
            case 5:
                return item.getTotal();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void add(InvoiceItem item) {
        invoice.getItems().add(item);
        invoice.setTotal(invoice.getTotal().add(item.getTotal()));
        fireTableDataChanged();
    }

    public void addTicket(BigDecimal price, int quantity, Projection projection, BigDecimal total) throws Exception {
        ArrayList<Ticket> tickets = Communcation.getInstance().getAllTicketsFromProjection(projection);
        int num = 0;
        for (int i = 0; i < quantity; i++) {
            while(true){
                if(tickets.size()<=num){
                    fireTableDataChanged();

                    throw new Exception("Projection full");
                }
                if(!map.containsKey(projection)) break;
                
                if(!map.get(projection).contains(tickets.get(num))){
                    break;
                }
                num++;
            }
            Ticket ticket = tickets.get(num);
            InvoiceItem item = new InvoiceItem(invoice, invoice.getItems().size()+1, price, 1, MeasurementUnit.PCS, ticket, price);
            invoice.getItems().add(item);
            invoice.setTotal(invoice.getTotal().add(item.getTotal()));
            if (!map.containsKey(projection)) {
                map.put(projection, new ArrayList<>());
            }
            map.get(projection).add(ticket);
            
            
        }
        
        fireTableDataChanged();
    }

    private Exception Exception(String projection_full) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
