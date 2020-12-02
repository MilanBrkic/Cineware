/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.Controller;
import coordinator.MainCoordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.model.table.UserTableModel;
import view.panel.PanelUserView;
import view.panel.mode.UserMode;

/**
 *
 * @author Brka
 */
public class ControllerUserView {
    private PanelUserView panel;
    private UserTableModel model;
    
    public ControllerUserView(PanelUserView panel) {
        this.panel = panel;
        model = new UserTableModel(Controller.getInstance().getDbUser().getAll());
    }

    public void openPanel() {
        panel.getTableUser().setModel(model);
        setListeners();
        prepareExitButton();
        panel.setVisible(true);
    }

    public PanelUserView getPanel() {
        return panel;
    }

    private void setListeners() {
        panel.getBtnDetails().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCoordinator.getInstance().openPanelUserAdd(UserMode.ADD);
            }
        });
    }

    private void prepareExitButton() {
        panel.getExitButton1().setPanel(panel);
    }
    
    
}
