/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
        setDatabaseText();
        icon.setIcon();
        
    }

    private void setListeners() {
        startServerListener();
        stopServerListener();
        saveDatabaseListener();
        
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

    private void setDatabaseText() {
        
        try {
            FileReader reader = new FileReader("resources/database.properties");
            Properties prop = new Properties();
            prop.load(reader);
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            
            form.getTxtUrl().setText(url);
            form.getTxtUser().setText(user);
            form.getTxtPassword().setText(password);
            
        } catch (Exception ex) {
            Logger.getLogger(ControllerView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveDatabaseListener() {
        form.getBtnSaveDatabase().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "url="+form.getTxtUrl().getText();
                String user = "user="+form.getTxtUser().getText();
                String password = "password="+form.getTxtPassword().getText();
                String all = url+"\n"+user+"\n"+password;
                try {
                    File file = new File("resources/database.properties");
                    PrintWriter myWriter = new PrintWriter(file);
                    myWriter.write(all);
                    JOptionPane.showMessageDialog(form, "Database confing changed", "Changed", JOptionPane.INFORMATION_MESSAGE);
                    myWriter.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(form, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(ControllerView.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
            }
        });
    }
    
    
}
