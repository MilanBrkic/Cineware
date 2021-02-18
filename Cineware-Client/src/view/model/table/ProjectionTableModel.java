/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model.table;

import communcation.Communcation;
import domain.Projection;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class ProjectionTableModel extends AbstractTableModel {

    private ArrayList<Projection> projections;
    private ArrayList<Projection> projectionsCopy;
    String sortValue = "";
    String[] columnNames = {"Movie", "Date", "Time", "Hall"};

    public ProjectionTableModel(ArrayList<Projection> projections) {
        this.projections = projections;
        projectionsCopy = new ArrayList<>(projections);
    }

    @Override
    public int getRowCount() {
        return projectionsCopy.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Projection p = projectionsCopy.get(rowIndex);

        switch (columnIndex) {
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
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void add(Projection p) {
        projections.add(p);
        sort();
    }

    public void delete(int index) throws Exception {
        Projection projection = projections.get(index);
        Communcation.getInstance().deleteProjection(projection);
        projections.remove(index);
        sort();
        fireTableDataChanged();
    }

    public ArrayList<Projection> getProjections() {
        return projectionsCopy;
    }

    public void refresh() throws Exception {
        Date date = new Date();
        ArrayList<Projection> projectionsTemp = Communcation.getInstance().getAllProjections();
        projections = new ArrayList<>();
        for (Projection projection : projectionsTemp) {
            if (date.before(projection.getStartDate())) {
                projections.add(projection);
            }
        }

        fireTableDataChanged();

    }

    public void setSortValue(String sort) {
        this.sortValue = sort;
    }

    public void sort() {
        projectionsCopy = new ArrayList<>();
        for (Projection projection : projections) {
            if(projection.getMovie().getName().contains(sortValue)){
                projectionsCopy.add(projection);
            }
        }
        fireTableDataChanged();
    }

}
