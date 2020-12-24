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
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import validation.Validation;

import view.model.table.UserTableModel;
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
        prepareForm(MainCoordinator.getInstance().getUser().isAdmin());
        setListeners();
        prepareExitButton();
        panel.setVisible(true);
    }

    public PanelUserAdd getPanel() {
        return panel;
    }

    private void prepareForm(boolean admin) {
        
        
        panel.getLblNewPassword().setVisible(false);
        panel.getTxtNewPassword().setVisible(false);
        
        switch (mode) {
            case ADD:
                panel.getBtnAdd().setVisible(true);
                panel.getBtnEdit().setVisible(false);
                panel.getBtnDelete().setVisible(false);
                panel.getBtnEnableChanges().setVisible(false);
                panel.getBtnCancel().setVisible(true);
                panel.getTxtID().setVisible(false);
                panel.getLblID().setVisible(false);
                break;
            case EDIT:
                buttonsForEdit();
                panel.getTxtPassword().setVisible(false);
                panel.getLblPassword().setVisible(false);             
                fillUserDetails();
                disableSwitch();
                break;
            case EDIT_PASSWORD:
                buttonsForEdit();
                panel.getLblNewPassword().setVisible(true);
                panel.getTxtNewPassword().setVisible(true);
                panel.getLblPassword().setVisible(true);
                panel.getTxtPassword().setVisible(true);
                panel.getBtnEnableChanges().setText("Change password");
                fillUserDetails();
                disableSwitch();
                break;
        }
    }
    
    public void buttonsForEdit(){
        panel.getBtnAdd().setVisible(false);
        panel.getBtnEdit().setVisible(true);
        panel.getBtnDelete().setVisible(true);
        panel.getBtnEnableChanges().setVisible(true);
        panel.getBtnEnableChanges().setEnabled(false);
        panel.getBtnCancel().setVisible(true);
        panel.getTxtID().setVisible(true);
        panel.getTxtID().setEnabled(false);
        panel.getLblID().setVisible(true);
    }
    
    public void disableSwitch(){
        panel.getBtnEdit().setEnabled(!panel.getBtnEdit().isEnabled());
        panel.getBtnDelete().setEnabled(!panel.getBtnDelete().isEnabled());
        panel.getBtnEnableChanges().setEnabled(!panel.getBtnEnableChanges().isEnabled());
        panel.getTxtFirstname().setEnabled(!panel.getTxtFirstname().isEnabled());
        panel.getTxtLastname().setEnabled(!panel.getTxtLastname().isEnabled());
        panel.getTxtUsername().setEnabled(!panel.getTxtUsername().isEnabled());
        panel.getRadioNo().setEnabled(!panel.getRadioNo().isEnabled());
        panel.getRadioYes().setEnabled(!panel.getRadioYes().isEnabled());
    }
    
    private void fillUserDetails() {
        User user = (User) MainCoordinator.getInstance().getParams().get(Constant.USER_DETAILS);
        panel.getTxtID().setText(String.valueOf(user.getId()));
        panel.getTxtFirstname().setText(user.getFirstname());
        panel.getTxtLastname().setText(user.getLastname());
        panel.getTxtUsername().setText(user.getUsername());
        if(user.isAdmin()) panel.getRadioYes().setSelected(true);
    }
    
    private void setListeners() {
        setAddListener();
        setEditListener();
        setExitListeners();
        setEnableChangesListener();
        setDeleteListener();
    }
    
    private void setDeleteListener(){
        panel.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    User user = (User) MainCoordinator.getInstance().getParams().get(Constant.USER_DETAILS);
                    int number = JOptionPane.showConfirmDialog(panel, "Are you sure you what to delete user "+user, "Delete", 0);
                    if(number==0) Communcation.getInstance().deleteUser(user);
                    UserTableModel model =  (UserTableModel) MainCoordinator.getInstance().getParams().get(Constant.USER_TABLE_MODEL);
                    model.refresh();
                    MainCoordinator.getInstance().removePanel(panel);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        
    }

    private void setEditListener(){
            panel.getBtnEdit().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        User user = forAdmin();
                        
                        
                        UserTableModel model =  (UserTableModel) MainCoordinator.getInstance().getParams().get(Constant.USER_TABLE_MODEL);
                        model.refresh();
                        JOptionPane.showMessageDialog(panel, "Updated user: "+user, "Updated", JOptionPane.INFORMATION_MESSAGE);
                        if(user.getId()==MainCoordinator.getInstance().getUser().getId()){
                            MainCoordinator.getInstance().getUser().setFirstname(user.getFirstname());
                            MainCoordinator.getInstance().getUser().setLastname(user.getLastname());
                            MainCoordinator.getInstance().getUser().setUsername(user.getUsername());
                            
                            MainCoordinator.getInstance().getUser().setAdmin(user.isAdmin());
                            MainCoordinator.getInstance().getControllerMain().setStatusBar();
                        }
                        MainCoordinator.getInstance().removePanel(panel);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
                
                public User forAdmin() throws Exception{
                    int id = Integer.parseInt(panel.getTxtID().getText());
                    String firstname = panel.getTxtFirstname().getText().trim();
                    String lastname = panel.getTxtLastname().getText().trim();
                    String username = panel.getTxtUsername().getText().trim();
                        
                    validationUpdate(firstname, lastname, username);
                    
                    
                    
                    User user = new User();
                    user.setId(id);
                    user.setFirstname(firstname);
                    user.setLastname(lastname);
                    user.setUsername(username);
                    if(panel.getRadioYes().isSelected()) user.setAdmin(true);
                    else user.setAdmin(false);
                    Communcation.getInstance().updateUser(user);
                    return user;
                }

                
            });
    }
    
    
    private void setEnableChangesListener() {
        panel.getBtnEnableChanges().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mode==mode.EDIT) forAdmin();
                else if(mode==mode.EDIT_PASSWORD) forUser();
            }
            
            private void forAdmin(){
                disableSwitch();
            }
        
            private void forUser(){
                try {
                    String oldPassword = String.valueOf(panel.getTxtPassword().getPassword());
                    oldPassword = MainCoordinator.getInstance().encrypt(oldPassword);
                    User user = (User) MainCoordinator.getInstance().getParams().get(Constant.USER_DETAILS);
                    boolean check = Communcation.getInstance().checkPassword(user.getUsername(),oldPassword);
                    if(!check) throw new Exception("Incorrect old password");
                    
                    String newPassword = String.valueOf(panel.getTxtNewPassword().getPassword());
                    newPassword = MainCoordinator.getInstance().encrypt(newPassword);
                    if(newPassword.equals(oldPassword)) throw new Exception("You have entered the same password");
                    
                    Communcation.getInstance().updatePasswordOnly(user.getUsername(), newPassword);
                    JOptionPane.showMessageDialog(panel, "User: "+user+" has changed his password", "Password changed", JOptionPane.INFORMATION_MESSAGE);
                    MainCoordinator.getInstance().logout();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private void setAddListener() {
        panel.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String firstname = panel.getTxtFirstname().getText().trim();
                    String lastname = panel.getTxtLastname().getText().trim();
                    String username = panel.getTxtUsername().getText().trim();
                    String password = String.valueOf(panel.getTxtPassword().getPassword());
                    boolean admin = false;
                    if (panel.getRadioYes().isSelected()) {
                        admin = true;
                    }

                    validation(firstname, lastname, username, password);
                    

                    
                    User user = new User(firstname, lastname, username, MainCoordinator.getInstance().encrypt(password), admin);
                    Communcation.getInstance().addUser(user);
                    
                    JOptionPane.showMessageDialog(panel, "User "+user+" is saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
                    clearAll();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

   
    
    private void validationUpdate(String firstname, String lastname, String username) throws Exception {
        String error = "";
        
        error += Validation.firstnameValidation(firstname);
        error += Validation.lastnameValidation(lastname);                
        error += Validation.usernameValidation(username);

        if(!isUniqueUpdate(username)) error+="Username already taken";
        
                
                
        if (!error.isEmpty()) throw new Exception(error);
    }
    
    
    private void validation(String firstname, String lastname, String username, String password) throws Exception {
        String error = "";
        
        error += Validation.firstnameValidation(firstname);
        error += Validation.lastnameValidation(lastname);                
        error += Validation.usernameValidation(username);
        
        if(!isUnique(username)) error+="Username already taken";

                
        if (password.length() < 4) error += "Password can not have less than 4 chars";
                
        if (!error.isEmpty()) throw new Exception(error);
    }

    private void clearAll() {
        panel.getTxtFirstname().setText("");
        panel.getTxtLastname().setText("");
        panel.getTxtUsername().setText("");
        panel.getTxtPassword().setText("");
        panel.getRadioNo().setSelected(true);
    }
    
    
    private boolean isUnique(String username) throws Exception {
        ArrayList<User> users = Communcation.getInstance().getAllUsers();
        for (User user : users) {
            if(user.getUsername().equals(username)) return false;
        }
        return true;
    }
    
    private boolean isUniqueUpdate(String username) throws Exception {
        User currentUser = (User) MainCoordinator.getInstance().getParams().get(Constant.USER_DETAILS);
        ArrayList<User> users = Communcation.getInstance().getAllUsers();
        for (User user : users) {
            if(user.getUsername().equals(username) && !user.getUsername().equals(currentUser.getUsername())) return false;
        }
        return true;
    }
    
    
    private void setExitListeners() {
        panel.getBtnCancel().addActionListener(new ExitListener());
    }

    private void prepareExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    

    class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainCoordinator.getInstance().removePanel(panel);
        }

    }
}
