/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model.table;

import domen.User;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Brka
 */
public class UserTableModel extends AbstractTableModel{
    ArrayList<User> users;
    String[] columnNames = new String[]{"Firstname", "Lastname", "Username", "Admin"};
    
    public UserTableModel(ArrayList<User> users) {
        this.users = users;
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
            case 0: return user.getFirstname();
            case 1: return user.getLastname();
            case 2: return user.getUsername();
            case 3: return user.isAdmin();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public ArrayList<User> getUsersList() {
        return users;
    }
    
    
    
}
