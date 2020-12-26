/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communcation.Communcation;
import domain.Actor;
import domain.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.constant.Constant;
import view.coordinator.MainCoordinator;
import view.model.table.ActorTableModel;
import view.panel.PanelActorView;
import view.panel.mode.ActorMode;

/**
 *
 * @author user
 */
public class ControllerActorView {
    PanelActorView panel;
    ActorTableModel model;

    public ControllerActorView(PanelActorView panel) {
        try {
            this.panel = panel;
            model = new ActorTableModel(Communcation.getInstance().getAllActors());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, ex.getMessage());
            Logger.getLogger(ControllerActorView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void openPanel(){
        setExitButton();
        panel.getTableActor().setModel(model);
        panel.setVisible(true);
        User user = MainCoordinator.getInstance().getUser();
        if(user.isAdmin()) panel.getBtnDetails().setVisible(true);
        else panel.getBtnDetails().setVisible(false);
        fillListeners();
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelActorView getPanel() {
        return panel;
    }

    private void fillListeners() {
        panel.getBtnDetails().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = panel.getTableActor().getSelectedRow();
                if(index>=0){
                    Actor d = model.getActors().get(index);
                    MainCoordinator.getInstance().getParams().put(Constant.ACTOR_DETAILS, d);
                    MainCoordinator.getInstance().getParams().put(Constant.ACTOR_TABLE_MODEL, model);
                    MainCoordinator.getInstance().openPanelActorAdd(ActorMode.EDIT);
                }
            }
        });
    }
}
