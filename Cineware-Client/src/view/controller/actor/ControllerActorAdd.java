/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller.actor;

import communcation.Communcation;
import domain.Actor;
import domain.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import view.model.table.ActorTableModel;
import view.panel.actor.PanelActorAdd;
import view.panel.mode.ActorMode;

/**
 *
 * @author Milan
 */
public class ControllerActorAdd {

    PanelActorAdd panel;
    ActorMode mode;

    public ControllerActorAdd(PanelActorAdd panel, ActorMode mode) {
        this.panel = panel;
        this.mode = mode;
    }

    public void openPanel() {
        fillNation();
        prepareForm();
        setExitButton();

        setListeners();
        panel.setVisible(true);
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelActorAdd getPanel() {
        return panel;
    }

    public void fillNation() {
        try {
            ArrayList<String> nations = Communcation.getInstance().getCountries();
            for (String nation : nations) {
                panel.getCmbNation().addItem(nation);
            }
        } catch (Exception ex) {
            Logger.getLogger(ControllerActorAdd.class.getName()).log(Level.SEVERE, null, ex);
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
                    Actor actor = getActorFromPanel();

                    Communcation.getInstance().addActor(actor);
                    JOptionPane.showMessageDialog(panel, "Actor added", "Added", JOptionPane.INFORMATION_MESSAGE);
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
                    Actor actor = getActorFromPanel();
                    System.out.println(actor);
                    Communcation.getInstance().updateActor(actor);
                    JOptionPane.showMessageDialog(panel, "Actor updated", "Updated", JOptionPane.INFORMATION_MESSAGE);
                    updateTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public void updateTable() throws Exception {
        ActorTableModel model = (ActorTableModel) MainCoordinator.getInstance().getParams().get(Constant.ACTOR_TABLE_MODEL);
        model.refresh();
        panel.getExitButton1().doClick();
    }

    private void setDeleteListener() {
        panel.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Actor actor = (Actor) MainCoordinator.getInstance().getParams().get(Constant.ACTOR_DETAILS);
                    int number = JOptionPane.showConfirmDialog(panel, "Are you sure you what to delete actor " + actor, "Delete", 0);
                    if (number == 0) {
                        Communcation.getInstance().deleteActor(actor);
                    }
                    updateTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(ControllerActorAdd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public Actor getActorFromPanel() throws Exception {
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

        Actor actor = null;
        if (mode == ActorMode.EDIT) {
            Actor actorDetails = (Actor) MainCoordinator.getInstance().getParams().get(Constant.ACTOR_DETAILS);
            int id = actorDetails.getId();
            actor = new Actor(id, firstname, lastname, dayOfBirth, country, user);
        } else {
            actor = new Actor(firstname, lastname, dayOfBirth, country, user);
        }

        return actor;
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

    public void toggleEnableFields() {
        panel.getTxtFirstname().setEnabled(!panel.getTxtFirstname().isEnabled());
        panel.getTxtLastname().setEnabled(!panel.getTxtLastname().isEnabled());
        panel.getCmbNation().setEnabled(!panel.getCmbNation().isEnabled());
        panel.getPanelDateInput().getCmbDay().setEnabled(!panel.getPanelDateInput().getCmbDay().isEnabled());
        panel.getPanelDateInput().getCmbMonth().setEnabled(!panel.getPanelDateInput().getCmbMonth().isEnabled());
        panel.getPanelDateInput().getCmbYear().setEnabled(!panel.getPanelDateInput().getCmbYear().isEnabled());

    }

    private void fillPanel() {
        Actor actor = (Actor) MainCoordinator.getInstance().getParams().get(Constant.ACTOR_DETAILS);
        panel.getTxtFirstname().setText(actor.getFirstname());
        panel.getTxtLastname().setText(actor.getLastname());

        GregorianCalendar greg = new GregorianCalendar();
        greg.setTime(actor.getDateOfBirth());
        int day = greg.get(GregorianCalendar.DAY_OF_MONTH);
        int month = greg.get(GregorianCalendar.MONTH) + 1;
        int year = greg.get(GregorianCalendar.YEAR);

        panel.getPanelDateInput().getCmbYear().setSelectedItem(year);
        panel.getPanelDateInput().getCmbMonth().setSelectedItem(month);
        panel.getPanelDateInput().getCmbDay().setSelectedItem(day);

        String nation = actor.getNationality();
        panel.getCmbNation().setSelectedItem(nation);
    }

}
