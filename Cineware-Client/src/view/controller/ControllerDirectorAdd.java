/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;


import communcation.Communcation;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
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
//        fillDate();
        setListeners();
        fillNation();
        panel.setVisible(true);
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelDirectorAdd getPanel() {
        return panel;
    }

    private void setListeners() {
//        monthListener();
//        yearListener();
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
    
//    private void fillDate() {
//        month31();
//
//        for (int i = 1; i <= 12; i++) {
//            panel.getPanelDateInput().getCmbMonth().addItem(i);
//        }
//        panel.getPanelDateInput().getCmbMonth().setSelectedIndex(-1);
//        int year = new GregorianCalendar().get(GregorianCalendar.YEAR);
//        for (int i = year; i >= 1900; i--) {
//            panel.getPanelDateInput().getCmbYear().addItem(i);
//        }
//        panel.getPanelDateInput().getCmbYear().setSelectedIndex(-1);
//    }
//
//    public void month31() {
//        panel.getPanelDateInput().getCmbDay().removeAllItems();
//        for (int i = 1; i <= 31; i++) {
//            panel.getPanelDateInput().getCmbDay().addItem(i);
//        }
//        panel.getPanelDateInput().getCmbDay().setSelectedIndex(-1);
//    }
//
//    public void month30() {
//        panel.getPanelDateInput().getCmbDay().removeAllItems();
//        for (int i = 1; i <= 30; i++) {
//            panel.getPanelDateInput().getCmbDay().addItem(i);
//        }
//        panel.getPanelDateInput().getCmbDay().setSelectedIndex(-1);
//    }
//
//    public void month29() {
//
//        panel.getPanelDateInput().getCmbDay().removeAllItems();
//        for (int i = 1; i <= 29; i++) {
//            panel.getPanelDateInput().getCmbDay().addItem(i);
//        }
//        panel.getPanelDateInput().getCmbDay().setSelectedIndex(-1);
//    }
//
//    public void month28() {
//        panel.getPanelDateInput().getCmbDay().removeAllItems();
//        for (int i = 1; i <= 28; i++) {
//            panel.getPanelDateInput().getCmbDay().addItem(i);
//        }
//        panel.getPanelDateInput().getCmbDay().setSelectedIndex(-1);
//    }
//
//    void monthListener() {
//        panel.getPanelDateInput().getCmbMonth().addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                int month = (int) panel.getPanelDateInput().getCmbMonth().getSelectedItem();
//                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
//                    month31();
//                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
//                    month30();
//                } else if (month == 2) {
//                    int year = (int) panel.getPanelDateInput().getCmbYear().getSelectedIndex();
//                    if (year == -1) {
//                        month28();
//                        return;
//                    }
//                    year = (int) panel.getPanelDateInput().getCmbYear().getSelectedItem();
//
//                    if (year % 4 == 0) {
//                        if (year % 400 == 0) {
//                            month29();
//                        } else if (year % 100 == 0) {
//                            month28();
//                        } else {
//                            month29();
//                        }
//                    } else {
//                        month28();
//                    }
//                }
//            }
//        });
//
//    }
//
//    void yearListener() {
//        panel.getPanelDateInput().getCmbYear().addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                int month = (int) panel.getPanelDateInput().getCmbMonth().getSelectedIndex();
//                if (month == -1) {
//                    month31();
//                    return;
//                }
//                month = (int) panel.getPanelDateInput().getCmbMonth().getSelectedItem();
//                
//
//                if (month == 2) {
//                    int year = (int) panel.getPanelDateInput().getCmbYear().getSelectedItem();
//
//                    if (year % 4 == 0) {
//                        if (year % 400 == 0) {
//                            month29();
//                        } else if (year % 100 == 0) {
//                            month28();
//                        } else {
//                            month29();
//                        }
//                    } else {
//                        month28();
//                    }
//                }
//            }
//        });
//
//    }

}
