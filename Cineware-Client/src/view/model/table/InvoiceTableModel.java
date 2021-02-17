/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model.table;

import communcation.Communcation;
import domain.Invoice;
import domain.InvoiceItem;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class InvoiceTableModel extends AbstractTableModel{

    ArrayList<Invoice> invoices;
    ArrayList<Invoice> invoicesCopy;
    String sortValue = "";
    
    String[] columnNames = {"Number", "Date", "Total", "User"};
    
    

    public InvoiceTableModel(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
        invoicesCopy = new ArrayList<>(invoices);
    }
    
    
    
    @Override
    public int getRowCount() {
        return invoicesCopy.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Invoice inv = invoicesCopy.get(rowIndex);
        switch(columnIndex){
            case 0:
                return inv.getNumber();
            case 1:
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                return sdf.format(inv.getDate());
            case 2:
                return inv.getUser();
            case 3:
                return inv.getTotal();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void setSortValue(String sortValue) {
        this.sortValue = sortValue;
    }
    
    public void sort(){
        invoicesCopy = new ArrayList<>();
        for (Invoice invoice : invoices) {
            if(invoice.getNumber().contains(sortValue)){
                invoicesCopy.add(invoice);
            }
        }
        fireTableDataChanged();
    }

    public BigDecimal getTotal() {
        double total = 0;
        for (Invoice invoice : invoicesCopy) {
            total+=invoice.getTotal().doubleValue();
        }
        return new BigDecimal(total);
    }

    public Invoice get(int index) {
        return invoicesCopy.get(index);
    }

    public Invoice getInverted(int index) throws Exception {
        Invoice i = invoicesCopy.get(index);
        if(alreadyStornoed(i)){
            throw new Exception("Already Storno");
        }
        Invoice inv = new Invoice(i.getId(), i.getNumber(), i.getDate(), i.getTotal(), i.getUser(), i.getItems());
        inv.setTotal(BigDecimal.ZERO);
        for (InvoiceItem item : inv.getItems()) {
            item.setPrice(item.getPrice().negate());
            item.setTotal(item.getTotal().negate());
            inv.setTotal(inv.getTotal().add(item.getTotal()));
        }
        return inv;
    }

    public void refresh() throws Exception {
        invoices = Communcation.getInstance().getAllInvoices();
        sort();
    }

    private boolean alreadyStornoed(Invoice i) {
        int number = 0;
        for (Invoice invoice : invoices) {
            if(invoice.getNumber().equals(i.getNumber())){
                number++;
            }
        }
        if(number>=2) return true;
        else return false;
    }
    
    
}
