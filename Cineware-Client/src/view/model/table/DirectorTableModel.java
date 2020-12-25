/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model.table;

import communcation.Communcation;
import domain.Director;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class DirectorTableModel extends AbstractTableModel{
    ArrayList<Director> directors;
    String[] columnNames = new String[]{"Firstname", "Lastname", "Date of Birth", "User"};

    public DirectorTableModel(ArrayList<Director> directors) {
        this.directors = directors;
    }
        
    
    @Override
    public int getRowCount() {
        return directors.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Director d = directors.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        switch(columnIndex){
            case 0: return d.getFirstname();
            case 1: return d.getLastname();
            case 2: return sdf.format(d.getDateOfBirth());
            case 3: return d.getUser().toString();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public ArrayList<Director> getDirectors() {
        return directors;
    }
    
    public void refresh() throws  Exception{
        directors = Communcation.getInstance().getAllDirectors();
        fireTableDataChanged();
    }
    
    
    
}
