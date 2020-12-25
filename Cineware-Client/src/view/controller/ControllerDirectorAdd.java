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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import validation.Validation;
import view.constant.Constant;
import view.coordinator.MainCoordinator;
import view.model.table.DirectorTableModel;
import view.panel.PanelDirectorAdd;
import view.panel.mode.DirectorMode;

/**
 *
 * @author Milan
 */
public class ControllerDirectorAdd {

    PanelDirectorAdd panel;
    DirectorMode mode;

    public ControllerDirectorAdd(PanelDirectorAdd panel, DirectorMode mode) {
        this.panel = panel;
        this.mode = mode;
    }

    public void openPanel() {
        prepareForm();
        setExitButton();
        fillNation();
        setListeners();
        panel.setVisible(true);
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelDirectorAdd getPanel() {
        return panel;
    }

    public void fillNation() {
        try {
            ArrayList<String> nations = Communcation.getInstance().getCountries();
            for (String nation : nations) {
                panel.getCmbNation().addItem(nation);
            }
        } catch (Exception ex) {
            Logger.getLogger(ControllerDirectorAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setListeners() {
        setAddListener();
        setEnableChangesListener();
        setUpdateListener();
        setDeleteListener();
    }

    private void setAddListener() {
        panel.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Director director = getDirectorFromPanel();

                    Communcation.getInstance().addDirector(director);
                    JOptionPane.showMessageDialog(panel, "Director added", "Added", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setEnableChangesListener() {
        panel.getBtnEnableChanges().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.getBtnEnableChanges().setEnabled(false);
                panel.getBtnDelete().setEnabled(true);
                panel.getBtnEdit().setEnabled(true);
                toggleEnableFields();
            }
        });
    }

    private void setUpdateListener() {
        panel.getBtnEdit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Director director = getDirectorFromPanel();
                    System.out.println(director);
                    Communcation.getInstance().updateDirector(director);
                    JOptionPane.showMessageDialog(panel, "Director updated", "Updated", JOptionPane.INFORMATION_MESSAGE);
                    DirectorTableModel model = (DirectorTableModel) MainCoordinator.getInstance().getParams().get(Constant.DIRECTOR_TABLE_MODEL);
                    model.refresh();
                    panel.getExitButton1().doClick();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void setDeleteListener() {
        panel.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Director director = (Director) MainCoordinator.getInstance().getParams().get(Constant.DIRECTOR_DETAILS);
                    int number = JOptionPane.showConfirmDialog(panel, "Are you sure you what to delete director "+director, "Delete", 0);
                    if(number==0) Communcation.getInstance().deleteDirector(director);
                    DirectorTableModel model = (DirectorTableModel) MainCoordinator.getInstance().getParams().get(Constant.DIRECTOR_TABLE_MODEL);
                    model.refresh();
                    panel.getExitButton1().doClick();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(ControllerDirectorAdd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public Director getDirectorFromPanel() throws Exception {
        String firstname = panel.getTxtFirstname().getText();
        String lastname = panel.getTxtLastname().getText();
        String error = "";

        error += Validation.firstnameValidation(firstname);
        error += Validation.lastnameValidation(lastname);

        if (!error.isEmpty()) {
            throw new Exception(error);
        }

        int day = (int) panel.getPanelDateInput().getCmbDay().getSelectedItem();
        int month = (int) panel.getPanelDateInput().getCmbMonth().getSelectedItem();
        int year = (int) panel.getPanelDateInput().getCmbYear().getSelectedItem();

        String datum = day + "." + month + "." + year + ".";
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        Date dayOfBirth = sdf.parse(datum);

        String country = (String) panel.getCmbNation().getSelectedItem();
        User user = MainCoordinator.getInstance().getUser();
        
        Director director= null;
        if(mode==DirectorMode.EDIT){
            Director directorDetails = (Director) MainCoordinator.getInstance().getParams().get(Constant.DIRECTOR_DETAILS);
            int id = directorDetails.getId();
            director = new Director(id,firstname, lastname, dayOfBirth, country, user);
        }else{
            director = new Director(firstname, lastname, dayOfBirth, country, user);
        }
        
        return director;
    }

    public void clearFields() {
        panel.getTxtFirstname().setText("");
        panel.getTxtLastname().setText("");
        panel.getPanelDateInput().getCmbDay().setSelectedItem(1);
        panel.getPanelDateInput().getCmbMonth().setSelectedItem(1);
        panel.getPanelDateInput().getCmbYear().setSelectedItem(2005);
    }

    private void prepareForm() {
        switch (mode) {
            case ADD:
                panel.getBtnAdd().setVisible(true);
                panel.getBtnDelete().setVisible(false);
                panel.getBtnEdit().setVisible(false);
                panel.getBtnEnableChanges().setVisible(false);
                break;
            case EDIT:
                panel.getBtnAdd().setVisible(false);
                panel.getBtnDelete().setVisible(true);
                panel.getBtnEdit().setVisible(true);
                panel.getBtnEnableChanges().setVisible(true);
                panel.getBtnEnableChanges().setEnabled(true);
                panel.getBtnEdit().setEnabled(false);
                panel.getBtnDelete().setEnabled(false);
                toggleEnableFields();
                fillPanel();
                break;
        }
    }
    
    public void toggleEnableFields(){
        panel.getTxtFirstname().setEnabled(!panel.getTxtFirstname().isEnabled());
        panel.getTxtLastname().setEnabled(!panel.getTxtLastname().isEnabled());
        panel.getCmbNation().setEnabled(!panel.getCmbNation().isEnabled());
        panel.getPanelDateInput().getCmbDay().setEnabled(!panel.getPanelDateInput().getCmbDay().isEnabled());
        panel.getPanelDateInput().getCmbMonth().setEnabled(!panel.getPanelDateInput().getCmbMonth().isEnabled());
        panel.getPanelDateInput().getCmbYear().setEnabled(!panel.getPanelDateInput().getCmbYear().isEnabled());
        
    }

    private void fillPanel() {
        Director director = (Director) MainCoordinator.getInstance().getParams().get(Constant.DIRECTOR_DETAILS);
        panel.getTxtFirstname().setText(director.getFirstname());
        panel.getTxtLastname().setText(director.getLastname());
        
        GregorianCalendar greg = new GregorianCalendar();
        greg.setTime(director.getDateOfBirth());
        int day = greg.get(GregorianCalendar.DAY_OF_MONTH);
        int month = greg.get(GregorianCalendar.MONTH)+1;
        int year = greg.get(GregorianCalendar.YEAR);
        panel.getPanelDateInput().getCmbDay().setSelectedItem(day);
        panel.getPanelDateInput().getCmbMonth().setSelectedItem(month);
        panel.getPanelDateInput().getCmbYear().setSelectedItem(year);
                
        String nation = director.getNationality();
        panel.getCmbNation().setSelectedItem(nation);
    }

}
