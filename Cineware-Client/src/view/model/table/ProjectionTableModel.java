/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model.table;

import communcation.Communcation;
import domain.Projection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class ProjectionTableModel extends AbstractTableModel{
    private ArrayList<Projection> projections;
    String[] columnNames = {"Movie", "Date", "Time","Hall"};
    
    public ProjectionTableModel(ArrayList<Projection> projections) {
        this.projections = projections;
    }

    @Override
    public int getRowCount() {
        return projections.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Projection p = projections.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return p.getMovie().toString();
            case 1:
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
                return sdf1.format(p.getStartDate());
            case 2:
                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
                return sdf2.format(p.getStartDate());
            case 3:
                return p.getHall().toString();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public void add(Projection p){
        projections.add(p);
        fireTableDataChanged();
    }

    public void delete(int index) throws Exception {
        Projection projection = projections.get(index);
        Communcation.getInstance().deleteProjection(projection);
        projections.remove(index);
        fireTableDataChanged();
    }
    
}
