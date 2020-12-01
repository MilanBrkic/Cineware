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
        panel.setVisible(true);
        prepareForm(mode, Controller.getInstance().getUser().isAdmin());
        setListeners();
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

                    isEmptyValidation(firstname, lastname, username, password);
                    
                    User user = new User(firstname, lastname, username, Controller.getInstance().encrypt(password), admin);
                    Controller.getInstance().getDbUser().add(user);
                    JOptionPane.showMessageDialog(panel, "User "+user+" is saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
                    clearAll();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void isEmptyValidation(String firstname, String lastname, String username, String password) throws Exception {
                String error = "";
                if (firstname.length() < 3) {
                    error += "Firstname can not have less than 3 chars\n";
                }
                if(firstname.contains("0123456789")) error+="Firstname can not contain a number";
                
                if (lastname.length() < 3) {
                    error += "Lastname can not have less than 3 chars\n";
                }
                if(lastname.contains("0123456789")) error+="Firstname can not contain a number";
                
                if (username.length() < 4) {
                    error += "Username can not have less than 4 chars\n";
                }
                if (password.length() < 4) {
                    error += "Password can not have less than 4 chars";
                }
                if (!error.isEmpty()) {
                    throw new Exception(error);
                }
            }

            private void clearAll() {
                panel.getTxtFirstname().setText("");
                panel.getTxtLastname().setText("");
                panel.getTxtUsername().setText("");
                panel.getTxtPassword().setText("");
                panel.getRadioNo().setSelected(true);
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
