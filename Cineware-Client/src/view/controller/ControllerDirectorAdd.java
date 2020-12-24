/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;


import communcation.Communcation;
import domain.Director;
import domain.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import validation.Validation;
import view.coordinator.MainCoordinator;
import view.panel.PanelDirectorAdd;

/**
 *
 * @author Milan
 */
public class ControllerDirectorAdd {

    PanelDirectorAdd panel;

    public ControllerDirectorAdd(PanelDirectorAdd panel) {
        this.panel = panel;
    }

    public void openPanel() {
        setExitButton();
        fillNation();
        addListener();
        panel.setVisible(true);
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelDirectorAdd getPanel() {
        return panel;
    }

    public void fillNation(){
        try {
            ArrayList<String> nations = Communcation.getInstance().getCountries();
            for (String nation : nations) {
                panel.getCmbNation().addItem(nation);
            }
        } catch (Exception ex) {
            Logger.getLogger(ControllerDirectorAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addListener() {
        panel.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String firstname = panel.getTxtFirstname().getText();
                    String lastname = panel.getTxtLastname().getText();
                    String error = "";
                    
                    error += Validation.firstnameValidation(firstname);
                    error += Validation.lastnameValidation(lastname);
                    
                    if(!error.isEmpty()) throw new Exception(error);
                    
                    int day = (int) panel.getPanelDateInput().getCmbDay().getSelectedItem();
                    int month = (int) panel.getPanelDateInput().getCmbMonth().getSelectedItem();
                    int year = (int) panel.getPanelDateInput().getCmbYear().getSelectedItem();
                    
                    String datum = day+"."+month+"."+year+".";
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                    Date dayOfBirth = sdf.parse(datum);
                    
                    String country = (String) panel.getCmbNation().getSelectedItem();
                    User user = MainCoordinator.getInstance().getUser();
                    Director director = new Director(firstname, lastname, dayOfBirth, country, user);
                    
                    Communcation.getInstance().addDirector(director);
                    JOptionPane.showMessageDialog(panel, "Director added", "Added", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    public void clearFields(){
        panel.getTxtFirstname().setText("");
        panel.getTxtLastname().setText("");
        panel.getPanelDateInput().getCmbDay().setSelectedItem(1);
        panel.getPanelDateInput().getCmbMonth().setSelectedItem(1);
        panel.getPanelDateInput().getCmbYear().setSelectedItem(2005);
    }

}
