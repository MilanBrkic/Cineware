/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.Controller;
import view.panel.PanelUserAdd;
import view.panel.mode.UserMode;

/**
 *
 * @author Brka
 */
public class ControllerUserAdd {
    PanelUserAdd panel;
    UserMode mode;

    public ControllerUserAdd(PanelUserAdd panel, UserMode mode) {
        this.panel = panel;
        this.mode = mode;
    }

    public void openPanel() {
        panel.setVisible(true);
        prepareForm(mode, Controller.getInstance().getUser().isAdmin());
    }

    public PanelUserAdd getPanel() {
        return panel;
    }
    
    private void prepareForm(UserMode mode, boolean admin) {
        if(!admin){
            panel.getRadioYes().setEnabled(false);
            panel.getRadioNo().setEnabled(false);
        }
        
        switch(mode){
            case ADD:
                panel.getBtnAdd().setVisible(true);
                panel.getBtnEdit().setVisible(false);
                panel.getBtnDelete().setVisible(false);
                panel.getBtnEnableChanges().setVisible(false);
                panel.getBtnCancel().setVisible(true);
                break;
            case EDIT:
                panel.getBtnAdd().setVisible(false);
                
                panel.getBtnEdit().setVisible(true);
                panel.getBtnDelete().setVisible(true);
                panel.getBtnEnableChanges().setVisible(true);
                panel.getBtnCancel().setVisible(true);
                break;
        }
    }
    
}
