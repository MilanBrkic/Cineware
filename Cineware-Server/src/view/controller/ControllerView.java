/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import server.Server;
import view.FormServer;
import view.util.Dots;
import view.util.IconSetter;

/**
 *
 * @author user
 */
public class ControllerView {
    public static ControllerView instance;
    private FormServer form;
    private Dots dots;
    Server server;
    IconSetter icon;
    
    private ControllerView() {
        form = new FormServer();
        icon = new IconSetter(form);
    }

    public static ControllerView getInstance() {
        if(instance==null) instance = new ControllerView();
        return instance;
    }

    public FormServer getForm() {
        return form;
    }
    
    public void startForm(){
        form.setLocationRelativeTo(null);
        form.setVisible(true);
        setListeners();
        icon.setIcon();
        
    }

    private void setListeners() {
        startServerListener();
        stopServerListener();
        
    }

    private void startServerListener() {
        form.getBtnStartServer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.getBtnStartServer().setEnabled(false);
                form.getBtnStopServer().setEnabled(true);
                dots = new Dots(form.getLblServerStatus());
                server = new Server();
                
            }
        });
    }

    private void stopServerListener() {
        form.getBtnStopServer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.getBtnStartServer().setEnabled(true);
                form.getBtnStopServer().setEnabled(false);
                form.getLblServerStatus().setText("Server is not running");
                dots.interrupt();
                try {
                    server.close();
                } catch (Exception ex) {
                }
            }
        });
    }
    
    
}
