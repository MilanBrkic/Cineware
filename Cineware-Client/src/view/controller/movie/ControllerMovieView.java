/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller.movie;

import communcation.Communcation;
import domain.Movie;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import view.constant.Constant;
import view.coordinator.MainCoordinator;
import view.model.table.MovieTableModel;
import view.panel.mode.Mode;
import view.panel.movie.PanelMovieView;

/**
 *
 * @author user
 */
public class ControllerMovieView {
    private PanelMovieView panel;
    private MovieTableModel model;
    
    public ControllerMovieView(PanelMovieView panel) {
        try {
            this.panel = panel;
            model = new MovieTableModel(Communcation.getInstance().getAllMovies());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ControllerMovieView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openPanel(){
        setExitButton();
        fillTable();
        panel.setVisible(true);
        setListeners();
    }
    
    public PanelMovieView getPanel() {
        return panel;
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    private void fillTable() {
        panel.getTableMovie().setModel(model);
        TableColumnModel columnModel = panel.getTableMovie().getColumnModel();
        
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(5);
        columnModel.getColumn(2).setPreferredWidth(5);
        columnModel.getColumn(3).setPreferredWidth(5);
        columnModel.getColumn(4).setPreferredWidth(60);
        columnModel.getColumn(5).setPreferredWidth(250);
    }

    private void setListeners() {
        setDetailsListener();
        setSearchListener();
    }

    private void setDetailsListener() {
        panel.getBtnDetails().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = panel.getTableMovie().getSelectedRow();
                if(index>=0){
                    Movie m = model.getMovies().get(index);
                    MainCoordinator.getInstance().getParams().put(Constant.MOVIE_DETAILS, m);
                    MainCoordinator.getInstance().getParams().put(Constant.MOVIE_TABLE_MODEL, model);
                    MainCoordinator.getInstance().openPanelMovieAdd(Mode.EDIT);
                }
            }
        });
    }

    private void setSearchListener() {
        panel.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sort = panel.getTxtSearch().getText();
                model.setSortValue(sort);
                model.sort();
            }
        });
    }
    
    
}
