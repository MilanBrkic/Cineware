/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import domen.User;
import java.util.ArrayList;
import java.util.Map;
import repository.Repository;
import repository.db.impl.DbUser;
/**
 *
 * @author Brka
 */
public class Controller {
    private Repository dbUser;
    private static Controller instance;
    private User user;
    
    
    private Controller(){
        dbUser = new DbUser();
    }
    
    public static Controller getInstance(){
        if(instance==null) instance = new Controller();
        return instance;
    }

    public Repository getDbUser() {
        return dbUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    
    
}
