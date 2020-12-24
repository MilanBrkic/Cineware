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
        fillNation();
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
    


}
