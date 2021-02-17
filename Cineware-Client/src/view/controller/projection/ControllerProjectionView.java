/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller.projection;

import communcation.Communcation;
import domain.Projection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import view.constant.Constant;
import view.coordinator.MainCoordinator;
import view.model.table.ProjectionTableModel;
import view.panel.mode.Mode;
import view.panel.projection.PanelProjectionView;

/**
 *
 * @author user
 */
public class ControllerProjectionView {

    PanelProjectionView panel;
    ProjectionTableModel previousModel;
    ProjectionTableModel upcomingModel;

    public ControllerProjectionView(PanelProjectionView panel) {
        this.panel = panel;
        previousModel = new ProjectionTableModel(new ArrayList<>());
        upcomingModel = new ProjectionTableModel(new ArrayList<>());

        panel.getTableUpcomingProjections().setModel(upcomingModel);
        panel.getTablePastProjections().setModel(previousModel);
    }

    public void openPanel() {
        panel.setVisible(true);
        setExitButton();
        try {
            setTables();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Could not load movies", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ControllerProjectionView.class.getName()).log(Level.SEVERE, null, ex);
        }
        setListeners();
        
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelProjectionView getPanel() {
        return panel;
    }

    private void setTables() throws Exception {
        ArrayList<Projection> projections = Communcation.getInstance().getAllProjections();
        ArrayList<Projection> previous = new ArrayList<>();
        ArrayList<Projection> upcoming = new ArrayList<>();

        Date date = new Date();
        for (Projection projection : projections) {
            if (date.after(projection.getStartDate())) {
                previous.add(projection);
            } else {
                upcoming.add(projection);
            }
        }

        for (Projection projection : upcoming) {
            upcomingModel.add(projection);
        }

        for (Projection projection : previous) {
            previousModel.add(projection);
        }

        TableColumnModel columnModel = panel.getTablePastProjections().getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(5);
        columnModel.getColumn(2).setPreferredWidth(5);
        columnModel.getColumn(3).setPreferredWidth(50);

        TableColumnModel columnModelUpcoming = panel.getTableUpcomingProjections().getColumnModel();

        columnModelUpcoming.getColumn(0).setPreferredWidth(150);
        columnModelUpcoming.getColumn(1).setPreferredWidth(5);
        columnModelUpcoming.getColumn(2).setPreferredWidth(5);
        columnModelUpcoming.getColumn(3).setPreferredWidth(50);
    }

    
    private void setListeners() {
        setDeleteListener();
        setUpdateListener();
    }

    
    private void setDeleteListener() {
        panel.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = panel.getTableUpcomingProjections().getSelectedRow();
                if (index >= 0) {
                    try {
                        int number = JOptionPane.showConfirmDialog(panel, "Are you sure you what to delete the projection?", "Delete", 0);
                        if (number == 0) {
                            upcomingModel.delete(index);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(ControllerProjectionView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Row is not selected", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setUpdateListener() {
        panel.getBtnDetails().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = panel.getTableUpcomingProjections().getSelectedRow();
                if(index>=0){
                    Projection projection = upcomingModel.getProjections().get(index);
                    MainCoordinator.getInstance().getParams().put(Constant.PROJECTION_DETAILS, projection);
                    MainCoordinator.getInstance().getParams().put(Constant.PROJECTION_TABLE_MODEL, upcomingModel);
                    MainCoordinator.getInstance().openPanelProjectionAdd(Mode.EDIT);
                }
            }
        });
    }

    
}
