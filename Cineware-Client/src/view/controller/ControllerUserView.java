/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communcation.Communcation;
import view.constant.Constant;
import view.coordinator.MainCoordinator;
import domain.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
        try {
            this.panel = panel;
            model = new UserTableModel(Communcation.getInstance().getAllUsers());
        } catch (Exception ex) {
            Logger.getLogger(ControllerUserView.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                int index = panel.getTableUser().getSelectedRow();
                if(index>=0){
                    User user = model.getUsersList().get(index);
                    MainCoordinator.getInstance().getParams().put(Constant.USER_DETAILS, user);
                    MainCoordinator.getInstance().getParams().put(Constant.USER_TABLE_MODEL, model); 
                    if(MainCoordinator.getInstance().getUser().isAdmin()) {
                        if(user.getUsername().equals(MainCoordinator.getInstance().getUser().getUsername())){    
                            if(adminUserOptionPane()==1){
                                MainCoordinator.getInstance().openPanelUserAdd(UserMode.EDIT_PASSWORD);
                                return;
                            }
                        }
                        MainCoordinator.getInstance().openPanelUserAdd(UserMode.EDIT);
                    }
                    else if(user.equals(MainCoordinator.getInstance().getUser())){
                                               
                        MainCoordinator.getInstance().openPanelUserAdd(UserMode.EDIT_PASSWORD);
                    }
                    else JOptionPane.showMessageDialog(panel, "Non admin can only see details of his account", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }
                
            }
        });
    }
    
    public int adminUserOptionPane(){
        String[] options = new String[]{"Admin", "User"};
        int number = JOptionPane.showOptionDialog(panel,
                "Do you want to go as Admin or as User",
                "Question",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options
                , options[0]);
        return number;
    }

    private void prepareExitButton() {
        panel.getExitButton1().setPanel(panel);
    }
    
    
}
