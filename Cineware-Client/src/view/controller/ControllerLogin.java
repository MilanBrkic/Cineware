/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communcation.Communcation;
import view.coordinator.MainCoordinator;
import domain.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import view.FormLogin;
import view.util.IconSetter;

/**
 *
 * @author Milan
 */
public class ControllerLogin {

    private IconSetter icon;
    private static ControllerLogin instance;
    private FormLogin form;

    public ControllerLogin(FormLogin form) {
        this.form = form;
        icon = new IconSetter(form);
    }

    public void openForm() {
        form.setLocationRelativeTo(null);
        form.setResizable(false);
        setIcon();
        setListeners();
        form.setVisible(true);
    }

    public void setIcon() {
        icon.setIcon();
    }

    private void setListeners() {
        setLoginListener();
        setEnterListeners();
    }

    private void setLoginListener() {
        form.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = form.getTxtUsername().getText().trim();
                String password = String.valueOf(form.getTxtPassword().getPassword());
                if (!validate(username, password)) {

                    return;
                }
                try {
                    User user = login(username, MainCoordinator.getInstance().encrypt(password));
                    MainCoordinator.getInstance().setUser(user);
                    Communcation.getInstance().login(user);
                    form.dispose();
                    MainCoordinator.getInstance().openFormMain();
                } catch (Exception ex) {
                    
                    form.getLblError().setText(ex.getMessage());
                    form.getTxtUsername().grabFocus();
                    
                }

            }

            private boolean validate(String username, String password) {
                form.getLblError().setText("");
                String error = "";

                if (username.equals("")) {
                    error = "Username can not be empty";
                    form.getLblError().setText(error);
                    return false;
                }

                if (password.equals("")) {
                    error += "Password can not be empty";
                    form.getLblError().setText(error);
                    return false;
                }
                return true;
            }

            private User login(String username, String password) throws Exception {

                ArrayList<User> users = new ArrayList<>();
                try {
                    users = Communcation.getInstance().getAllUsers();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (User user : users) {
                    if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                        return user;
                    }
                }
                throw new Exception("username or password incorrect");

            }
        });
    }

    

    private void setEnterListeners() {
        form.getTxtUsername().addKeyListener(new EnterListener());
        form.getTxtPassword().addKeyListener(new EnterListener());
    }

    class EnterListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                form.getBtnLogin().doClick();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }
    
        
    
}
