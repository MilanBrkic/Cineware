/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model.table;

import domain.Invoice;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class InvoiceTableModel extends AbstractTableModel{

    ArrayList<Invoice> invoices;
    String[] columnNames = {"Number", "Date", "Total", "User"};
    
    

    public InvoiceTableModel(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }
    
    
    
    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Invoice inv = invoices.get(rowIndex);
        switch(columnIndex){
            case 0:
                return inv.getNumber();
            case 1:
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                return sdf.format(inv.getDate());
            case 2:
                return inv.getTotal();
            case 3:
                return inv.getUser();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    
    
}
