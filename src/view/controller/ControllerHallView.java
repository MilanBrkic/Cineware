/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import controller.Controller;
import view.model.table.HallTableModel;
import view.panel.PanelHallView;

/**
 *
 * @author Milan
 */
public class ControllerHallView {
    private PanelHallView panel;
    private HallTableModel model;
    
    public ControllerHallView(PanelHallView panel) {
        this.panel = panel;
        model = new HallTableModel(Controller.getInstance().getDbHall().getAll());
    }

    public PanelHallView getPanel() {
        return panel;
    }

    public void openPanel() {
        panel.getTableHall().setModel(model);
        prepareExitButton();
        panel.getTableHall().setRowHeight(26);
        panel.getTableHall().getColumnModel().getColumn(1).setPreferredWidth(10);
        panel.setVisible(true);
    }

    private void prepareExitButton() {
        panel.getExitButton1().setPanel(panel);
    }    
    
}
