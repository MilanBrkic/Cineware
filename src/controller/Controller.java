/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import domen.User;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Map;
import repository.Repository;
import repository.db.impl.DbHall;
import repository.db.impl.DbUser;
/**
 *
 * @author Milan
 */
public class Controller {
    private Repository dbUser;
    private Repository dbHall;
    private static Controller instance;
    private User user;
    
    
    private Controller(){
        dbUser = new DbUser();
        dbHall = new DbHall();
    }
    
    public static Controller getInstance(){
        if(instance==null) instance = new Controller();
        return instance;
    }

    public Repository getDbUser() {
        return dbUser;
    }

    public Repository getDbHall() {
        return dbHall;
    }
   

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    
}
