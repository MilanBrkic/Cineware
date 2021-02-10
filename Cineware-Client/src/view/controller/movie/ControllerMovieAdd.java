/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller.movie;

import communcation.Communcation;
import domain.Actor;
import domain.Director;
import domain.Genre;
import domain.Movie;
import domain.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.coordinator.MainCoordinator;
import view.model.table.ActorForMovieTableModel;
import view.panel.movie.PanelMovieAdd;
import view.panel.mode.ActorMode;
import view.panel.mode.DirectorMode;
import view.panel.mode.MovieMode;

/**
 *
 * @author user
 */
public class ControllerMovieAdd {
    PanelMovieAdd panel;
    MovieMode mode;
    ActorForMovieTableModel model;
    
    
    public ControllerMovieAdd(PanelMovieAdd panel, MovieMode mode) {
        this.panel = panel;
        this.mode = mode;
        model = new ActorForMovieTableModel(new ArrayList<>());
    }
    
    public void openPanel(){
        panel.setVisible(true);
        setExitButton();
        fillForm();
        setListeners();
    }

    public PanelMovieAdd getPanel() {
        return panel;
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    private void fillForm() {
        panel.getTxtDescription().setLineWrap(true);
        try {
            panel.getCmbGenre().removeAllItems();
            for (Genre genre : Genre.values()) {
                panel.getCmbGenre().addItem(genre);
            }
            
            fillcmbActor();
            fillCmbDirector();
            
            
            panel.getTableActor().setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ControllerMovieAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillcmbActor() throws Exception{
        panel.getCmbActor().removeAllItems();
            ArrayList<Actor> actors = Communcation.getInstance().getAllActors();            
            for (Actor actor : actors) {
                panel.getCmbActor().addItem(actor);
            }
    }
    
    public void fillCmbDirector() throws Exception{
        panel.getCmbDirector().removeAllItems();
            ArrayList<Director> directors = Communcation.getInstance().getAllDirectors();
            for (Director director : directors) {
                panel.getCmbDirector().addItem(director);
            }
    }
    
    private void setListeners() {
        setAddActorListener();
        setDeleteActorListener();
        setSaveMovieListener();
        setAddNewDirector();
        setAddNewActor();
    }

    private void setAddActorListener() {
        panel.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Actor actor= (Actor) panel.getCmbActor().getSelectedItem();
                    if(actor==null) return;
                    model.add(actor);
                    panel.getCmbActor().removeItem(actor);
               
            }
        });
    }

    
    
    private void setDeleteActorListener() {
        panel.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = panel.getTableActor().getSelectedRow();
                    if(index<0) throw new Exception("No actor is selected");
                    panel.getCmbActor().addItem(model.getActors().get(index));
                    model.remove(index);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(ControllerMovieAdd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void setSaveMovieListener() {
        panel.getBtnSaveMovie().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String error ="";
                    String name = panel.getTxtName().getText();
                    if(name.isEmpty()) error+="Name can not be empty\n";
                    
                    String description = panel.getTxtDescription().getText();
                    if(description.isEmpty()) error+="Description can not be empty\n";
                    
                    int runtime = 0;
                    String runtimeString = panel.getTxtRuntime().getText();
                    if(runtimeString.isEmpty()) error+="Runtime can not be empty\n";
                    else{
                        runtime = Integer.parseInt(runtimeString);
                        if(runtime<30 || runtime>250) error+="Runtime must be between 30 and 250 minutes\n";
                    }
                    
                    int year = 0;
                    String yearString = panel.getTxtYear().getText();
                    if(yearString.isEmpty())error+="Year can not be empty";
                    else{
                        year = Integer.parseInt(yearString);
                        int limit = new GregorianCalendar().get(GregorianCalendar.YEAR);
                        if(year<1900 || year>limit) error +="Year must be berween 1900 and "+limit;
                    }
                    if(error!="") throw new Exception(error);
                    
                    Genre genre = (Genre) panel.getCmbGenre().getSelectedItem();
                    Director director = (Director) panel.getCmbDirector().getSelectedItem();
                    ArrayList<Actor> actors = model.getActors();
                    User user = MainCoordinator.getInstance().getUser();
                    
                    Movie movie = new Movie(name, description, genre, runtime,year, director, actors, user);
                    Communcation.getInstance().addMovie(movie);
                    JOptionPane.showMessageDialog(panel, "Movie "+movie.getName()+" added", "Added", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(ControllerMovieAdd.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }
    
    
    private void setAddNewDirector() {
        panel.getBtnAddNewDirector().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCoordinator.getInstance().openPanelDirectorAdd(DirectorMode.ADD);
                
            }
        });
        
        panel.getBtnRefreshDirectors().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fillCmbDirector();
                } catch (Exception ex) {
                    Logger.getLogger(ControllerMovieAdd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void setAddNewActor() {
        panel.getBtnAddNewActor().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCoordinator.getInstance().openPanelActorAdd(ActorMode.ADD);
            }
        });
        
        panel.getBtnRefreshActors().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fillcmbActor();
                } catch (Exception ex) {
                    Logger.getLogger(ControllerMovieAdd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void clearFields() throws Exception{
        panel.getTxtName().setText("");
        panel.getTxtDescription().setText("");
        panel.getTxtRuntime().setText("");
        panel.getTxtYear().setText("");
        model.removeAllItems();
        fillcmbActor();
    }

    

    
    
}
