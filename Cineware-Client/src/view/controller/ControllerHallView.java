/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communcation.Communcation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.model.table.HallTableModel;
import view.panel.PanelHallView;

/**
 *
 * @author Milan
 */
public class ControllerHallView {
    private PanelHallView panel;
    private HallTableModel model;
    
    public ControllerHallView(PanelHallView panel) {
        try {
            this.panel = panel;
            model = new HallTableModel(Communcation.getInstance().getAllHalls());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, ex.getMessage());
            Logger.getLogger(ControllerHallView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PanelHallView getPanel() {
        return panel;
    }

    public void openPanel() {
        panel.getTableHall().setModel(model);
        prepareExitButton();
        panel.getTableHall().setRowHeight(26);
        panel.getTableHall().getColumnModel().getColumn(1).setPreferredWidth(10);
        panel.setVisible(true);
    }

    private void prepareExitButton() {
        panel.getExitButton1().setPanel(panel);
    }    
    
}
