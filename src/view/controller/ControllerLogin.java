/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.security.MessageDigest;
import javax.swing.ImageIcon;
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

    public void encrypt(String password) {

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

            System.out.println("Plain    : " + password);
            System.out.println("Encrypted: " + sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListeners() {
        setLoginListener();
        setEnterListeners();
    }

    private void setLoginListener() {
        form.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!validate()) return;
            }

            private boolean validate() {
                form.getLblError().setText("");
                String error = "";
                String username = form.getTxtUsername().getText().trim();
                if(username.equals("")){
                    error = "Username can not be empty";
                    form.getLblError().setText(error);
                    return false;
                }
                
                String password = String.valueOf(form.getTxtPassword().getPassword());
                if(password.equals("")){
                    error += "Password can not be empty";
                    form.getLblError().setText(error);
                    return false;
                }
                return true;
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

