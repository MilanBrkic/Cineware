/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import constant.Constant;
import controller.Controller;
import coordinator.MainCoordinator;
import domen.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import repository.db.impl.DbUser;
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
        prepareForm(mode, Controller.getInstance().getUser().isAdmin());
        setListeners();
        prepareExitButton();
        panel.setVisible(true);
    }

    public PanelUserAdd getPanel() {
        return panel;
    }

    private void prepareForm(UserMode mode, boolean admin) {
        
        
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
                panel.getBtnAdd().setVisible(false);
                panel.getBtnEdit().setVisible(true);
                panel.getBtnDelete().setVisible(true);
                panel.getBtnEnableChanges().setVisible(true);
                panel.getBtnEnableChanges().setEnabled(false);
                panel.getBtnCancel().setVisible(true);
                panel.getTxtID().setVisible(true);
                panel.getTxtID().setEnabled(false);
                panel.getLblID().setVisible(true);
                if(admin){
                    panel.getTxtPassword().setVisible(false);
                    panel.getLblPassword().setVisible(false);
                }
                else{
                    panel.getLblNewPassword().setVisible(true);
                    panel.getTxtNewPassword().setVisible(true);
                    panel.getLblPassword().setVisible(true);
                    panel.getTxtPassword().setVisible(true);
                    panel.getBtnEnableChanges().setText("Change password");
                }
                
                
                fillUserDetails();
                disableSwitch();
                break;
        }
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
                    if(number==0) Controller.getInstance().getDbUser().delete(user);
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
                        if(user.getId()==Controller.getInstance().getUser().getId()){
                            Controller.getInstance().getUser().setFirstname(user.getFirstname());
                            Controller.getInstance().getUser().setLastname(user.getLastname());
                            Controller.getInstance().getUser().setUsername(user.getUsername());
                            
                            Controller.getInstance().getUser().setAdmin(user.isAdmin());
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
                    Controller.getInstance().getDbUser().update(user);
                    return user;
                }

                
            });
    }
    
    
    private void setEnableChangesListener() {
        panel.getBtnEnableChanges().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Controller.getInstance().getUser().isAdmin()) forAdmin();
                else forUser();
            }
            
            private void forAdmin(){
                disableSwitch();
            }
        
            private void forUser(){
                try {
                    String oldPassword = String.valueOf(panel.getTxtPassword().getPassword());
                    oldPassword = Controller.getInstance().encrypt(oldPassword);
                    User user = (User) MainCoordinator.getInstance().getParams().get(Constant.USER_DETAILS);
                    DbUser db = (DbUser) Controller.getInstance().getDbUser();
                    if(!db.checkPassword(user.getUsername(), oldPassword)) throw new Exception("Incorrect old password");
                    
                    String newPassword = String.valueOf(panel.getTxtNewPassword().getPassword());
                    newPassword = Controller.getInstance().encrypt(newPassword);
                    if(newPassword.equals(oldPassword)) throw new Exception("You have entered the same password");
                    
                    db.updatePasswordOnly(user.getUsername(), newPassword);
                    JOptionPane.showMessageDialog(panel, "User: "+user+" has changed his password", "Password changed", JOptionPane.INFORMATION_MESSAGE);                    
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
                    

                    
                    User user = new User(firstname, lastname, username, Controller.getInstance().encrypt(password), admin);
                    Controller.getInstance().getDbUser().add(user);
                    
                    JOptionPane.showMessageDialog(panel, "User "+user+" is saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
                    clearAll();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public String firstnameValidation(String firstname){
        String error="";
        if (firstname.length() < 3) error += "Firstname can not have less than 3 chars\n";
        else{
            String regex="[A-Z]+[a-z0-9]+";
            if(!firstname.matches(regex)) error+="Firstname must start with a capital\n";
            else{
                regex="[A-Z]+[a-z]+";
                if(!firstname.matches(regex)) error+="Firstname can not have a number\n"; 
            }                    
        }
        return error;
    }
    
    public String lastnameValidation(String lastname){
        String error = "";
        if (lastname.length() < 3) error += "Lastname can not have less than 3 chars\n";
        else{
            String regex="[A-Z]+[a-z0-9]+";
            if(!lastname.matches(regex)) error+="Lastname must start with a capital\n";
            else{
                regex="[A-Z]+[a-z]+";
                if(!lastname.matches(regex)) error+="Lastname can not have a number\n"; 
            }
        }
        return error;
    }
    
    public String usernameValidation(String username){
        String error = "";
        
        if (username.length() < 4) error += "Username can not have less than 4 chars\n";
        else{
            String regex ="[A-Za-z0-9.]+";
            if(!username.matches(regex)) error+="Username can not gave blanks and special characters";
            
        }
        return error;
    }
    
    private void validationUpdate(String firstname, String lastname, String username) throws Exception {
        String error = "";
        
        error += firstnameValidation(firstname);
        error += lastnameValidation(lastname);                
        error += usernameValidation(username);

        if(!isUniqueUpdate(username)) error+="Username already taken";
        
                
                
        if (!error.isEmpty()) throw new Exception(error);
    }
    
    
    private void validation(String firstname, String lastname, String username, String password) throws Exception {
        String error = "";
        
        error += firstnameValidation(firstname);
        error += lastnameValidation(lastname);                
        error += usernameValidation(username);
        
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
    
    
    private boolean isUnique(String username) {
        ArrayList<User> users = Controller.getInstance().getDbUser().getAll();
        for (User user : users) {
            if(user.getUsername().equals(username)) return false;
        }
        return true;
    }
    
    private boolean isUniqueUpdate(String username) {
        User currentUser = (User) MainCoordinator.getInstance().getParams().get(Constant.USER_DETAILS);
        ArrayList<User> users = Controller.getInstance().getDbUser().getAll();
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
