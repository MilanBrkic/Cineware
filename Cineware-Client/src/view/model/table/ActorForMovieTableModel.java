/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model.table;

import domain.Actor;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class ActorForMovieTableModel extends AbstractTableModel{

    ArrayList<Actor> actors;
    String[] columnNames = new String[]{"Name"};

    public ActorForMovieTableModel(ArrayList<Actor> actors) {
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
        Actor actor = actors.get(rowIndex);
        return actor.getLastname()+" "+actor.getFirstname();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    
    public void add(Actor actor){
        actors.add(actor);
        fireTableDataChanged();
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    
    
    public void remove(int index) {
        actors.remove(index);
        fireTableDataChanged();

    }
    
    public void removeAllItems(){
        actors.clear();
        fireTableDataChanged();
    }
}
