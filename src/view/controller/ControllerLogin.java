/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

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

    
    public void openForm(){
        form.setLocationRelativeTo(null);
        setIcon();
        form.setVisible(true);
    }
    
    public void setIcon(){
        String basePath = new File("").getAbsolutePath();
        String iconPath = basePath+ "\\resources\\cineware-icon.png";
        
        ImageIcon img = new ImageIcon(iconPath);
        form.setIconImage(img.getImage());
        
    }
    
    public void encrypt(String password){
        
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
}
