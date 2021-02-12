/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller.projection;

import communcation.Communcation;
import domain.Hall;
import domain.Movie;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.panel.mode.ProjectionMode;
import view.panel.projection.PanelProjectionAdd;

/**
 *
 * @author user
 */
public class ControllerProjectionAdd {
    private PanelProjectionAdd panel;
    private ProjectionMode mode;

    public ControllerProjectionAdd(PanelProjectionAdd panel, ProjectionMode mode) {
        this.panel = panel;
        this.mode = mode;
    }
    
    public void openPanel(){
        panel.setVisible(true);
        setExitButton();
        fillForm();
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelProjectionAdd getPanel() {
        return panel;
    }

    private void fillForm() {
        for(int i = 0;i<=24;i++){
            if(i<10)
                panel.getJcmbHour().addItem("0"+i);
            else
                panel.getJcmbHour().addItem(i);
        }
        
        panel.getJcmbMinute().addItem("00");
        panel.getJcmbMinute().addItem("15");
        panel.getJcmbMinute().addItem("30");
        panel.getJcmbMinute().addItem("45");
        
        
        try {
            ArrayList<Movie> movies;
            movies = Communcation.getInstance().getAllMovies();
            for (Movie movy : movies) {
                panel.getJcmbMovie().addItem(movy);
            
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Could not load movies", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ControllerProjectionAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ArrayList<Hall> halls = Communcation.getInstance().getAllHalls();
            for (Hall hall : halls) {
                panel.getJcmbHall().addItem(hall);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, "Could not load halls", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ControllerProjectionAdd.class.getName()).log(Level.SEVERE, null, e);
        }
        
        fillDate();
        
    }

    private void fillDate() {
        GregorianCalendar greg = new GregorianCalendar();
        int day = greg.get(GregorianCalendar.DAY_OF_MONTH);
        int month = greg.get(GregorianCalendar.MONTH)+1;
        
        panel.getJcmbMonth().addItem(month);
        panel.getJcmbMonth().addItem(month+1);
        firstMonth(month, day);
        
        
        panel.getJcmbMonth().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = panel.getJcmbMonth().getSelectedIndex();
                if(index>=0){
                    if(index==0){
                        firstMonth(month, day);
                    }
                    else {
                        secondMonth(day);
                    }
                }
            }
        });
        
    }
    
    
    public void firstMonth(int month, int day){
        panel.getJcmbDay().removeAllItems();
        int limit = month==2? 28: ((month==4 || month==6 || month==9 || month==11)? 30:31); 
        
        for(int i = day;i<=limit;i++){
            panel.getJcmbDay().addItem(i);
        }
    }
    
    public void secondMonth(int day){
        panel.getJcmbDay().removeAllItems();
        for(int i = 0;i<=day;i++){
            panel.getJcmbDay().addItem(i);
        }
    }
    
}
