/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller.projection;

import communcation.Communcation;
import domain.Projection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import view.model.table.ProjectionTableModel;
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

}
