/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communcation.Communcation;
import domain.Director;
import domain.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.constant.Constant;
import view.coordinator.MainCoordinator;
import view.model.table.DirectorTableModel;
import view.panel.PanelDirectorView;
import view.panel.mode.DirectorMode;

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
        fillListeners();
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelDirectorView getPanel() {
        return panel;
    }

    private void fillListeners() {
        panel.getBtnDetails().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = panel.getTableDirector().getSelectedRow();
                if(index>=0){
                    Director d = model.getDirectors().get(index);
                    MainCoordinator.getInstance().getParams().put(Constant.DIRECTOR_DETAILS, d);
                    MainCoordinator.getInstance().getParams().put(Constant.DIRECTOR_TABLE_MODEL, model);
                    MainCoordinator.getInstance().openPanelDirectorAdd(DirectorMode.EDIT);
                }
            }
        });
    }
}
