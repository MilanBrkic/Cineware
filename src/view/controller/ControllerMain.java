/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.Controller;
import coordinator.MainCoordinator;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
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

    public ControllerMain(FormMain form) {
        this.form = form;
        icon = new IconSetter(form);
    }
    
    public void setIcon(){
        icon.setIcon();
    }

    public FormMain getForm() {
        return form;
    }
    
    public void openForm(){
        form.setMinimumSize(new Dimension(600, 400));
        form.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        form.getLblWelcomeUser().setText("Welcome: "+Controller.getInstance().getUser());
        setIcon();
        setListeners();
        
        form.setLocationRelativeTo(null);
        form.getJMenuBar().setOpaque(false);
        if(!Controller.getInstance().getUser().isAdmin()) form.getMenuItemUserAdd().setEnabled(false);
        setMenuLogout();
        
        form.setVisible(true);
    }
    
    private void setMenuLogout() {
        JMenu logout = new JMenu("Account");
        
        
    }

    public void setIcon(IconSetter icon) {
        this.icon = icon;
    }

    private void setListeners() {
        setUserListeners(); 
        
        
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


    
    
    
    
}
