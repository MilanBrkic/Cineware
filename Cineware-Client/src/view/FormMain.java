/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.awt.MenuBar;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Brka
 */
public class FormMain extends javax.swing.JFrame {

    /** Creates new form FormMain */
    public FormMain() {
        initComponents();
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PanelStatusBar = new javax.swing.JPanel();
        lblStatusBarUser = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        PanelMain = new javax.swing.JPanel();
        lblWelcomeUser = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuItemActorAdd = new javax.swing.JMenuItem();
        menuItemActorView = new javax.swing.JMenuItem();
        menuDirector = new javax.swing.JMenu();
        menuItemDirectorAdd = new javax.swing.JMenuItem();
        menuItemDirectorView = new javax.swing.JMenuItem();
        menuHall = new javax.swing.JMenu();
        menuItemHallView = new javax.swing.JMenuItem();
        menuUser = new javax.swing.JMenu();
        menuItemUserAdd = new javax.swing.JMenuItem();
        menuItemUserView = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cineware");

        PanelStatusBar.setBackground(new java.awt.Color(0, 0, 0));
        PanelStatusBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelStatusBar.setForeground(new java.awt.Color(255, 255, 255));
        PanelStatusBar.setLayout(new java.awt.BorderLayout());

        lblStatusBarUser.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblStatusBarUser.setForeground(new java.awt.Color(255, 255, 255));
        lblStatusBarUser.setText("User:");
        PanelStatusBar.add(lblStatusBarUser, java.awt.BorderLayout.CENTER);

        lblTime.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        PanelStatusBar.add(lblTime, java.awt.BorderLayout.LINE_END);

        getContentPane().add(PanelStatusBar, java.awt.BorderLayout.PAGE_END);

        lblWelcomeUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        PanelMain.add(lblWelcomeUser);

        getContentPane().add(PanelMain, java.awt.BorderLayout.CENTER);

        menuBar.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setText("Actor");

        menuItemActorAdd.setText("Add");
        jMenu1.add(menuItemActorAdd);

        menuItemActorView.setText("View");
        jMenu1.add(menuItemActorView);

        menuBar.add(jMenu1);

        menuDirector.setText("Director");

        menuItemDirectorAdd.setText("Add");
        menuDirector.add(menuItemDirectorAdd);

        menuItemDirectorView.setText("View");
        menuDirector.add(menuItemDirectorView);

        menuBar.add(menuDirector);

        menuHall.setText("Hall");

        menuItemHallView.setText("View");
        menuHall.add(menuItemHallView);

        menuBar.add(menuHall);

        menuUser.setBackground(new java.awt.Color(255, 255, 255));
        menuUser.setText("User");

        menuItemUserAdd.setBackground(new java.awt.Color(255, 255, 255));
        menuItemUserAdd.setText("Add");
        menuUser.add(menuItemUserAdd);

        menuItemUserView.setText("View");
        menuUser.add(menuItemUserView);

        menuBar.add(menuUser);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JLabel getLblWelcomeUser() {
        return lblWelcomeUser;
    }

    public JMenuItem getMenuItemUserAdd() {
        return menuItemUserAdd;
    }

    public JMenuItem getMenuItemUserView() {
        return menuItemUserView;
    }

    public JMenuBar returnMenuBar(){
        return menuBar;
    }
    
    

    public JPanel getPanelMain() {
        return PanelMain;
    }

    public JPanel getPanelStatusBar() {
        return PanelStatusBar;
    }

    public JLabel getLblStatusBarUser() {
        return lblStatusBarUser;
    }

    public JLabel getLblTime() {
        return lblTime;
    }

    public JMenuItem getMenuItemHallView() {
        return menuItemHallView;
    }

    public JMenuItem getMenuItemDirectorAdd() {
        return menuItemDirectorAdd;
    }

    public JMenuItem getMenuItemDirectorView() {
        return menuItemDirectorView;
    }

    public JMenuItem getMenuItemActorAdd() {
        return menuItemActorAdd;
    }

    public JMenuItem getMenuItemActorView() {
        return menuItemActorView;
    }

    

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPanel PanelStatusBar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblStatusBarUser;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblWelcomeUser;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuDirector;
    private javax.swing.JMenu menuHall;
    private javax.swing.JMenuItem menuItemActorAdd;
    private javax.swing.JMenuItem menuItemActorView;
    private javax.swing.JMenuItem menuItemDirectorAdd;
    private javax.swing.JMenuItem menuItemDirectorView;
    private javax.swing.JMenuItem menuItemHallView;
    private javax.swing.JMenuItem menuItemUserAdd;
    private javax.swing.JMenuItem menuItemUserView;
    private javax.swing.JMenu menuUser;
    // End of variables declaration//GEN-END:variables

}
