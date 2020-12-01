/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.Controller;
import coordinator.MainCoordinator;
import domen.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
        panel.setVisible(true);
    }

    public PanelUserAdd getPanel() {
        return panel;
    }

    private void prepareForm(UserMode mode, boolean admin) {
        if (!admin) {
            panel.getRadioYes().setEnabled(false);
            panel.getRadioNo().setEnabled(false);
        }

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
                panel.getBtnCancel().setVisible(true);
                panel.getTxtID().setVisible(true);
                panel.getLblID().setVisible(true);
                break;
        }
    }

    private void setListeners() {
        setAddListener();
        setExitListeners();
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

            private void validation(String firstname, String lastname, String username, String password) throws Exception {
                String error = "";
                if (firstname.length() < 3) error += "Firstname can not have less than 3 chars\n";
                else{
                    String regex="[A-Z]+[a-z0-9]+";
                    if(!firstname.matches(regex)) error+="Firstname must start with a capital\n";
                    else{
                       regex="[A-Z]+[a-z]+";
                        if(!firstname.matches(regex)) error+="Firstname can not have a number\n"; 
                    }                    
                }
                
                
                if (lastname.length() < 3) error += "Lastname can not have less than 3 chars\n";
                else{
                    String regex="[A-Z]+[a-z0-9]+";
                    if(!lastname.matches(regex)) error+="Lastname must start with a capital\n";
                    else{
                       regex="[A-Z]+[a-z]+";
                        if(!lastname.matches(regex)) error+="Lastname can not have a number\n"; 
                    }
                }
                
                
                if (username.length() < 4) error += "Username can not have less than 4 chars\n";
                else{
                    String regex ="[A-Za-z0-9.]+";
                    if(!username.matches(regex)) error+="Username can not gave blanks and special characters";
                    else{
                        if(!isUnique(username)) error+="Username already taken";
                    }
                }
                
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
        });
    }

    private void setExitListeners() {
        panel.getBtnX().addActionListener(new ExitListener());
        panel.getBtnCancel().addActionListener(new ExitListener());
    }

    class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainCoordinator.getInstance().getControllerMain().getForm().getContentPane().remove(panel);
            MainCoordinator.getInstance().getControllerMain().getForm().invalidate();
            MainCoordinator.getInstance().getControllerMain().getForm().validate();
            MainCoordinator.getInstance().getControllerMain().getForm().repaint();
        }

    }
}
