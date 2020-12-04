/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import clock.MyClock;
import controller.Controller;
import coordinator.MainCoordinator;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import view.FormMain;
import view.panel.mode.UserMode;
import view.util.IconSetter;

/**
 *
 * @author Brka
 */
public class ControllerMain {
    private IconSetter icon;
    private FormMain form;
    private JMenuItem menuItemLogOut;
    private MyClock clock;
    
    public ControllerMain(FormMain form) {
        this.form = form;
        icon = new IconSetter(form);
        clock = new MyClock(form.getLblTime());
    }
    
    public void setIcon(){
        icon.setIcon();
    }

    public FormMain getForm() {
        return form;
    }
    
    public void openForm(){
        setSizeAndLocation();
        startClock();
        setIcon();
        setMenuBar();
        setStatusBar();
        setListeners();
       
        form.getLblWelcomeUser().setText("Welcome: "+Controller.getInstance().getUser()); 
        form.setVisible(true);
    }
    
    public void startClock(){
        clock.start();
    }
    
    
    
    public void setMenuBar(){
        form.getJMenuBar().setOpaque(false);
        if(!Controller.getInstance().getUser().isAdmin()) form.getMenuItemUserAdd().setEnabled(false);
        addMenuAccount();
    }
    
    private void addMenuAccount() {
        JMenu menu = new JMenu("Account");
        form.returnMenuBar().add(Box.createHorizontalGlue());
        form.returnMenuBar().add(menu);
        menuItemLogOut = new JMenuItem("Log-out");
        menu.add(menuItemLogOut);
    }
    
    
    public void setSizeAndLocation(){
        form.setMinimumSize(new Dimension(600, 400));
        form.setExtendedState(JFrame.MAXIMIZED_BOTH);
        form.setLocationRelativeTo(null);
    }
    
    public void setStatusBar(){
        form.getLblStatusBarUser().setText("User: "+ Controller.getInstance().getUser());
    }
    
    
    public void setIcon(IconSetter icon) {
        this.icon = icon;
    }

    private void setListeners() {
        setLogoutListener();
        setUserListeners();     
    }

    public void setLogoutListener(){
        menuItemLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCoordinator.getInstance().logout();
            }
        });
    }
    
    private void setUserListeners() {
        form.getMenuItemUserAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCoordinator.getInstance().removeAllPanels();
                enableAll();
                form.getMenuItemUserAdd().setEnabled(false);
                form.getLblWelcomeUser().setVisible(false);
                MainCoordinator.getInstance().openPanelUserAdd(UserMode.ADD);
            }
        });
        
        form.getMenuItemUserView().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCoordinator.getInstance().removeAllPanels();
                enableAll();
                form.getMenuItemUserView().setEnabled(false);
                form.getLblWelcomeUser().setVisible(false);
                MainCoordinator.getInstance().openPanelUserView();
            }
        });
    }
    
    public void enableAll(){
        if(Controller.getInstance().getUser().isAdmin()) form.getMenuItemUserAdd().setEnabled(true);
        form.getMenuItemUserView().setEnabled(true);
    }

    public MyClock getClock() {
        return clock;
    }

    
}
