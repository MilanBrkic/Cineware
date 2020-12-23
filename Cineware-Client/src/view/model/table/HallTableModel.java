/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model.table;

import domain.Hall;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Milan
 */
public class HallTableModel extends AbstractTableModel{
    ArrayList<Hall> halls;
    private String[] columnNames = new String[]{"Name", "No. of seats"};
    
    public HallTableModel(ArrayList<Hall> halls) {
        this.halls = halls;
    }

    @Override
    public int getRowCount() {
        return halls.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Hall h = halls.get(rowIndex);
        switch(columnIndex){
            case 0: return h.getName();
            case 1: return h.getNumberOfSeats();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    
    
    
}
