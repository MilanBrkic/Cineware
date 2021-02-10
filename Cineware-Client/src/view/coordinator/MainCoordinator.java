
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.coordinator;

import communcation.Communcation;
import domain.User;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import view.FormLogin;
import view.FormMain;
import view.constant.Constant;
import view.controller.actor.ControllerActorAdd;
import view.controller.actor.ControllerActorView;
import view.controller.director.ControllerDirectorAdd;
import view.controller.director.ControllerDirectorView;
import view.controller.hall.ControllerHallView;
import view.controller.ControllerLogin;
import view.controller.ControllerMain;
import view.controller.movie.ControllerMovieAdd;
import view.controller.movie.ControllerMovieView;
import view.controller.user.ControllerUserAdd;
import view.controller.user.ControllerUserView;
import view.panel.actor.PanelActorAdd;
import view.panel.actor.PanelActorView;
import view.panel.director.PanelDirectorAdd;
import view.panel.director.PanelDirectorView;
import view.panel.hall.PanelHallView;
import view.panel.movie.PanelMovieAdd;
import view.panel.movie.PanelMovieView;
import view.panel.user.PanelUserAdd;
import view.panel.user.PanelUserView;
import view.panel.mode.ActorMode;
import view.panel.mode.DirectorMode;
import view.panel.mode.MovieMode;
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
    

    public void openPanelHallView() {
        ControllerHallView hallView = new ControllerHallView(new PanelHallView());
        addPanel(hallView.getPanel());
        hallView.openPanel();
    }
    
     public void openPanelDirectorAdd(DirectorMode mode) {
        ControllerDirectorAdd directorAdd = new ControllerDirectorAdd(new PanelDirectorAdd(), mode);
        addPanel(directorAdd.getPanel());
        directorAdd.openPanel();
    }
    
    public void openPanelDirectorView() {
        ControllerDirectorView directorView = new ControllerDirectorView(new PanelDirectorView());
        addPanel(directorView.getPanel());
        directorView.openPanel();
    }
    
    public void openPanelActorAdd(ActorMode mode) {
        ControllerActorAdd actorAdd = new ControllerActorAdd(new PanelActorAdd(), mode);
        addPanel(actorAdd.getPanel());
        actorAdd.openPanel();
    }
    
    public void openPanelActorView() {
        ControllerActorView actorView = new ControllerActorView(new PanelActorView());
        addPanel(actorView.getPanel());
        actorView.openPanel();
    }
    
    public void openPanelMovieAdd(MovieMode movieMode) {
        ControllerMovieAdd movieAdd = new ControllerMovieAdd(new PanelMovieAdd(), movieMode);
        addPanel(movieAdd.getPanel());
        movieAdd.openPanel();
    }
    
    public void openPanelMovieView() {
        ControllerMovieView movieView = new ControllerMovieView(new PanelMovieView());
        addPanel(movieView.getPanel());
        movieView.openPanel();
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

    public void logout() throws Exception {
        Communcation.getInstance().logout(getUser());
        setUser(null);
        controllerMain.getForm().dispose();
        controllerMain.getClock().interrupt();
        openFormLogin();
        controllerMain = new ControllerMain(new FormMain());
        
        
    }

    

    public void setUser(User user){
        params.put(Constant.LOGGED_USER, user);
    }
    
    public User getUser(){
        return (User) params.get(Constant.LOGGED_USER);
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
