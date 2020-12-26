/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model.table;

import communcation.Communcation;
import domain.Actor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class ActorTableModel extends AbstractTableModel{
    ArrayList<Actor> actors;
    String[] columnNames = new String[]{"Firstname", "Lastname", "Date of Birth", "User"};

    public ActorTableModel(ArrayList<Actor> actors) {
        this.actors = actors;
    }
        
    
    @Override
    public int getRowCount() {
        return actors.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Actor d = actors.get(rowIndex);
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

    public ArrayList<Actor> getActors() {
        return actors;
    }
    
    public void refresh() throws  Exception{
        actors = Communcation.getInstance().getAllActors();
        fireTableDataChanged();
    }
    
    
    
}
