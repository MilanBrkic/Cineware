/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordinator;

import view.FormLogin;
import view.FormMain;
import view.controller.ControllerLogin;
import view.controller.ControllerMain;
import view.controller.ControllerUserAdd;
import view.controller.ControllerUserView;
import view.panel.PanelUserAdd;
import view.panel.PanelUserView;
import view.panel.mode.UserMode;

/**
 *
 * @author Milan
 */
public class MainCoordinator {
    private static MainCoordinator instance;
    private ControllerMain controllerMain;
            
            
    private MainCoordinator() {
        controllerMain=new ControllerMain(new FormMain());
    }
    
    
    public static MainCoordinator getInstance(){
        if(instance==null) instance=new MainCoordinator();
        return instance;
    }
    
    public void openFormLogin(){
        ControllerLogin login = new ControllerLogin(new FormLogin());
        login.openForm();
    }
    
    public void openFormMain(){
        controllerMain.openForm();
    }

    public ControllerMain getControllerMain() {
        return controllerMain;
    }
    
    public void openPanelUserAdd(UserMode mode){
        ControllerUserAdd userAdd = new ControllerUserAdd(new PanelUserAdd(), mode);
        controllerMain.getForm().getContentPane().add(userAdd.getPanel());
        userAdd.openPanel();
        
    }

    public void openPanelUserView() {
        ControllerUserView userView = new ControllerUserView(new PanelUserView());
        controllerMain.getForm().getContentPane().removeAll();
        controllerMain.getForm().getContentPane().add(userView.getPanel());
        controllerMain.getForm().invalidate();
        controllerMain.getForm().validate();
        controllerMain.getForm().repaint();
        
        userView.openPanel();
        
    }
    
}
