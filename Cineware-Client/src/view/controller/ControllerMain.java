/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import communcation.Communcation;
import view.util.MyClock;
import view.coordinator.MainCoordinator;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import view.FormMain;
import view.panel.mode.DirectorMode;
import view.panel.mode.UserMode;
import view.util.IconSetter;

/**
 *
 * @author Milan
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
       
        form.getLblWelcomeUser().setText("Welcome: "+MainCoordinator.getInstance().getUser()); 
        form.setVisible(true);
    }
    
    public void startClock(){
        clock.start();
    }
    
    
    
    public void setMenuBar(){
        form.getJMenuBar().setOpaque(false);
        if(!MainCoordinator.getInstance().getUser().isAdmin()) form.getMenuItemUserAdd().setEnabled(false);
        if(!MainCoordinator.getInstance().getUser().isAdmin()) form.getMenuItemDirectorAdd().setEnabled(false);
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
        form.getLblStatusBarUser().setText("User: "+ MainCoordinator.getInstance().getUser());
    }
    
    
    public void setIcon(IconSetter icon) {
        this.icon = icon;
    }

    private void setListeners() {
        setLogoutListener();
        setUserListeners();   
        setHallListeners();
        setDirectorListeners();
        setWindowListener();
        
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
    
    private void setHallListeners() {
        form.getMenuItemHallView().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCoordinator.getInstance().removeAllPanels();
                enableAll();
                form.getMenuItemHallView().setEnabled(false);
                form.getLblWelcomeUser().setVisible(false);
                MainCoordinator.getInstance().openPanelHallView();
            }
        });
    }
    
    private void setDirectorListeners() {
        form.getMenuItemDirectorAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCoordinator.getInstance().removeAllPanels();
                enableAll();
                form.getMenuItemDirectorAdd().setEnabled(false);
                form.getLblWelcomeUser().setVisible(false);
                MainCoordinator.getInstance().openPanelDirectorAdd(DirectorMode.ADD);
            }
        });
        form.getMenuItemDirectorView().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCoordinator.getInstance().removeAllPanels();
                enableAll();
                form.getMenuItemDirectorView().setEnabled(false);
                form.getLblWelcomeUser().setVisible(false);
                MainCoordinator.getInstance().openPanelDirectorView();
            }
        });
    }
    
    public void enableAll(){
        if(MainCoordinator.getInstance().getUser().isAdmin()) form.getMenuItemUserAdd().setEnabled(true);
        if(MainCoordinator.getInstance().getUser().isAdmin()) form.getMenuItemDirectorAdd().setEnabled(true);
        form.getMenuItemUserView().setEnabled(true);
        form.getMenuItemHallView().setEnabled(true);
        form.getMenuItemDirectorAdd().setEnabled(true);
        form.getMenuItemDirectorView().setEnabled(true);
    }

    public MyClock getClock() {
        return clock;
    }

    private void setWindowListener() {
        form.addWindowListener(new WindowListener() {
            

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Communcation.getInstance().closeSocket();
                } catch (IOException ex) {
                    Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

            @Override
            public void windowOpened(WindowEvent e) {
            }
        });
    }

    

    

    
}
