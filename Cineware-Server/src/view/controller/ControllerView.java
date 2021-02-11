/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.Controller;
import domain.User;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import server.Server;
import view.FormServer;
import view.model.ColorColumnCellRenderer;
import view.model.UserTableModel;
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
    UserTableModel model;

    private ControllerView() {
        form = new FormServer();
        icon = new IconSetter(form);
    }

    public static ControllerView getInstance() {
        if (instance == null) {
            instance = new ControllerView();
        }
        return instance;
    }

    public FormServer getForm() {
        return form;
    }

    public void startForm() {
        form.setLocationRelativeTo(null);
        form.setVisible(true);
        setListeners();
        setDatabaseText();
        form.setMinimumSize(new Dimension(670, 535));
        icon.setIcon();
        form.getTableUsers().setModel(new UserTableModel());

    }

    private void setListeners() {
        startServerListener();
        stopServerListener();
        saveDatabaseListener();
        windowListener();
        
    }

    private void startServerListener() {
        form.getBtnStartServer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.getBtnStartServer().setEnabled(false);
                form.getBtnStopServer().setEnabled(true);
                dots = new Dots(form.getLblServerStatus());
                server = new Server();
                try {
                    fillTable();
                    colorTable();

                } catch (Exception ex) {
                    Logger.getLogger(ControllerView.class.getName()).log(Level.SEVERE, null, ex);
                }

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
                form.getTableUsers().setModel(new UserTableModel());

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
                String url = "url=" + form.getTxtUrl().getText();
                String user = "user=" + form.getTxtUser().getText();
                String password = "password=" + form.getTxtPassword().getText();
                String all = url + "\n" + user + "\n" + password;

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

    private void fillTable() throws Exception {
        ArrayList<User> users = Controller.getInstance().getAllUsers();
        Map<String, String> map = fillMap(users);
        model = new UserTableModel(users, map);
        form.getTableUsers().setModel(model);
    }

    private HashMap<String, String> fillMap(ArrayList<User> users) {
        Map<String, String> map = new HashMap<>();
        for (User user : users) {
            map.put(user.getUsername(), "offline");
        }
        return (HashMap<String, String>) map;
    }

    public void newLoggedInUser(User user) {
        model.newLoggedInUser(user);

    }
    
    public void loggedOutUser(User userOut) {
        model.newLoggedOutUser(userOut);
    }
    
    private void colorTable() {
        form.getTableUsers().getColumnModel().getColumn(1).setCellRenderer(new ColorColumnCellRenderer());
    }

    private void windowListener() {
        form.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    server.close();
                } catch (Exception ex) {
                    Logger.getLogger(ControllerView.class.getName()).log(Level.SEVERE, null, ex);
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
        });
    }

    

}
