/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordinator;

import view.FormLogin;
import view.controller.ControllerLogin;

/**
 *
 * @author Milan
 */
public class MainCoordinator {
    private static MainCoordinator instance;

    private MainCoordinator() {
    }
    
    
    public static MainCoordinator getInstance(){
        if(instance==null) instance=new MainCoordinator();
        return instance;
    }
    
    public void openFormLogin(){
        ControllerLogin login = new ControllerLogin(new FormLogin());
        login.openForm();
    }
    
}
