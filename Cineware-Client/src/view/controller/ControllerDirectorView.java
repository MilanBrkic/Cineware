/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communcation.Communcation;
import domain.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.coordinator.MainCoordinator;
import view.model.table.DirectorTableModel;
import view.panel.PanelDirectorView;

/**
 *
 * @author user
 */
public class ControllerDirectorView {
    PanelDirectorView panel;
    DirectorTableModel model;

    public ControllerDirectorView(PanelDirectorView panel) {
        try {
            this.panel = panel;
            model = new DirectorTableModel(Communcation.getInstance().getAllDirectors());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, ex.getMessage());
            Logger.getLogger(ControllerDirectorView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void openPanel(){
        setExitButton();
        panel.getTableDirector().setModel(model);
        panel.setVisible(true);
        User user = MainCoordinator.getInstance().getUser();
        if(user.isAdmin()) panel.getBtnDetails().setVisible(true);
        else panel.getBtnDetails().setVisible(false);
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelDirectorView getPanel() {
        return panel;
    }
}
