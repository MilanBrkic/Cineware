/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model;

import domain.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class UserTableModel extends AbstractTableModel{

    ArrayList<User> users;
    Map<String,String> map;
    String[] columnNames = {"User", "Status"};
    
    public UserTableModel(ArrayList<User> users, Map<String,String> map) {
        this.users = users;
        this.map = map;
    }

    public UserTableModel() {
        users = new ArrayList<>();
        map = new HashMap<>();
    }
    
    
    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        User user = users.get(rowIndex);
        switch(columnIndex){
            case 0:
                return user.getUsername();
            case 1:
                return map.get(user.getUsername());
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void newLoggedInUser(User user) {
        map.put(user.getUsername(), "online");
        fireTableDataChanged();
    }

    public void newLoggedOutUser(User user) {
        map.put(user.getUsername(), "offline");
        fireTableDataChanged();
    }

    
    
}
