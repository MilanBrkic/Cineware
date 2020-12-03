/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.util;

import coordinator.MainCoordinator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Brka
 */
public class ExitButton extends JButton{
    JPanel panel;
    
    public ExitButton() {
        super("X");
        setBackground(Color.RED);
        setForeground(Color.white);
        setSize(new Dimension(33, 23));
        addActionListener(new ExitListener());
        
        
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    
    
    
    class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainCoordinator.getInstance().removePanel(panel);
            
            MainCoordinator.getInstance().getControllerMain().enableAll();
        }

    }
}
