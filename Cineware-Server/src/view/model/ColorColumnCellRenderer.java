/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author user
 */
public class ColorColumnCellRenderer extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if(l.getText().equals("offline")){
            l.setForeground(Color.white);
            l.setBackground(new Color(220, 20, 60));
        }
        else {
            l.setForeground(Color.white);
            l.setBackground(new Color(0,102,51));
        }
        
        
        return l;
    }
    
    
    
}
