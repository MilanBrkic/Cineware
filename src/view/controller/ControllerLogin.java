/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.Controller;
import domen.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import view.FormLogin;

/**
 *
 * @author Milan
 */
public class ControllerLogin {

    private static ControllerLogin instance;
    private FormLogin form;

    public ControllerLogin(FormLogin form) {
        this.form = form;
    }

    public void openForm() {
        form.setLocationRelativeTo(null);
        setIcon();
        setListeners();
        form.setVisible(true);
    }

    public void setIcon() {
        String basePath = new File("").getAbsolutePath();
        String iconPath = basePath + "\\resources\\cineware-icon.png";

        ImageIcon img = new ImageIcon(iconPath);
        form.setIconImage(img.getImage());

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
                   User user = login(username, encrypt(password));
                    System.out.println(user);
                } catch (Exception ex) {
                    form.getLblError().setText(ex.getMessage());
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
                    users = Controller.getInstance().getDbUser().getAll();
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

    public String encrypt(String password) {

        String algorithm = "SHA";

        byte[] plainText = password.getBytes();

        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);

            md.reset();
            md.update(plainText);
            byte[] encodedPassword = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < encodedPassword.length; i++) {
                if ((encodedPassword[i] & 0xff) < 0x10) {
                    sb.append("0");
                }

                sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
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
