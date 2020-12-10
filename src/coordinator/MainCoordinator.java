/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordinator;

import clock.MyClock;
import controller.Controller;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import view.FormLogin;
import view.FormMain;
import view.controller.ControllerDirectorAdd;
import view.controller.ControllerHallView;
import view.controller.ControllerLogin;
import view.controller.ControllerMain;
import view.controller.ControllerUserAdd;
import view.controller.ControllerUserView;
import view.panel.PanelDirectorAdd;
import view.panel.PanelHallView;
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
    private Map<String,Object> params;

    private MainCoordinator() {
        controllerMain=new ControllerMain(new FormMain());
        params = new HashMap<>();
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

    public Map<String, Object> getParams() {
        return params;
    }
    

    
    public void openPanelUserAdd(UserMode mode){
        ControllerUserAdd userAdd = new ControllerUserAdd(new PanelUserAdd(), mode);
        addPanel(userAdd.getPanel());
        userAdd.openPanel();
        
    }

    public void openPanelUserView() {
        ControllerUserView userView = new ControllerUserView(new PanelUserView());
        addPanel(userView.getPanel());
        userView.openPanel();
        
    }
    
    public void openPanelDirectorAdd() {
        ControllerDirectorAdd directorAdd = new ControllerDirectorAdd(new PanelDirectorAdd());
        addPanel(directorAdd.getPanel());
        directorAdd.openPanel();
    }
    
    public void openPanelHallView() {
        ControllerHallView hallView = new ControllerHallView(new PanelHallView());
        addPanel(hallView.getPanel());
        hallView.openPanel();
    }
    
    public void removePanel(JPanel panel){
        controllerMain.getForm().getPanelMain().remove(panel);
        controllerMain.getForm().invalidate();
        controllerMain.getForm().validate();
        controllerMain.getForm().repaint();
    }
    
    public void addPanel(JPanel panel){
        controllerMain.getForm().getPanelMain().add(panel);
        controllerMain.getForm().invalidate();
        controllerMain.getForm().validate();
        controllerMain.getForm().repaint();
    }
    
    public void removeAllPanels(){
        controllerMain.getForm().getPanelMain().removeAll();
        controllerMain.getForm().invalidate();
        controllerMain.getForm().validate();
        controllerMain.getForm().repaint();
    }

    public void logout() {
        Controller.getInstance().setUser(null);
        controllerMain.getForm().dispose();
        controllerMain.getClock().interrupt();
        openFormLogin();
        controllerMain = new ControllerMain(new FormMain());
        
    }

    

    
    
    
}
