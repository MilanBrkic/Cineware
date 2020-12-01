/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.Controller;
import javax.swing.JFrame;
import view.FormMain;
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
        form.setExtendedState(JFrame.MAXIMIZED_BOTH);
        form.getLblWelcomeUser().setText("Welcome: "+Controller.getInstance().getUser());
        setIcon();
        form.setVisible(true);
    }

    public void setIcon(IconSetter icon) {
        this.icon = icon;
    }
    
    
}
