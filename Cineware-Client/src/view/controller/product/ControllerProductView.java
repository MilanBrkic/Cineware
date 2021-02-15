/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller.product;

import communcation.Communcation;
import domain.Product;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import view.constant.Constant;
import view.coordinator.MainCoordinator;
import view.model.table.ProductTableModel;
import view.panel.mode.Mode;
import view.panel.product.PanelProductView;

/**
 *
 * @author user
 */
public class ControllerProductView {
    PanelProductView panel;
    ProductTableModel model;
    
    public ControllerProductView(PanelProductView panel) {
        this.panel = panel;
        
    }
    
    public void openPanel(){
        panel.setVisible(true);
        setExitButton();
        fillTable();
        setListeners();
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelProductView getPanel() {
        return panel;
    }

    private void fillTable() {
        try {
            model = new ProductTableModel(Communcation.getInstance().getAllProducts());
            panel.getTableProduct().setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Can't load table", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ControllerProductView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TableColumnModel columnModel = panel.getTableProduct().getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(5);
        columnModel.getColumn(2).setPreferredWidth(5);
        columnModel.getColumn(3).setPreferredWidth(70);
        columnModel.getColumn(4).setPreferredWidth(5);
    }

    private void setListeners() {
        setSearchListener();
        setProductListener();
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

    private void setProductListener() {
        panel.getBtnDetails().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = panel.getTableProduct().getSelectedRow();
                if(index>=0){
                    Product p = model.getProducts().get(index);
                    MainCoordinator.getInstance().getParams().put(Constant.PRODUCT_DETAILS, p);
                    MainCoordinator.getInstance().getParams().put(Constant.PRODUCT_TABLE_MODEL, model);
                    MainCoordinator.getInstance().openPanelProductAdd(Mode.EDIT);
                }
            }
        });
    }
    
    
    
}
