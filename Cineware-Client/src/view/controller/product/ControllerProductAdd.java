/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller.product;

import communcation.Communcation;
import domain.Product;
import domain.User;
import domain.enums.MeasurementUnit;
import domain.enums.ProductType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import view.coordinator.MainCoordinator;
import view.panel.mode.Mode;
import view.panel.product.PanelProductAdd;

/**
 *
 * @author user
 */
public class ControllerProductAdd {

    PanelProductAdd panel;
    Mode mode;

    public ControllerProductAdd(PanelProductAdd panel, Mode mode) {
        this.panel = panel;
        this.mode = mode;
    }

    public void openPanel() {
        panel.setVisible(true);
        fillTable();
        setExitButton();
        setListeners();
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelProductAdd getPanel() {
        return panel;
    }

    private void fillTable() {

        panel.getCmbUnit().addItem(MeasurementUnit.G);
        panel.getCmbUnit().addItem(MeasurementUnit.L);
        panel.getCmbUnit().addItem(MeasurementUnit.PCS);

        panel.getCmbType().addItem(ProductType.FOOD);
        panel.getCmbType().addItem(ProductType.DRINK);

    }

    private void setListeners() {
        panel.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String error = "";
                    String name = panel.getTxtName().getText();
                    if(name.isEmpty()) error += "Name can't be empty\n";
                    BigDecimal price = null;
                    try {
                        price = new BigDecimal(panel.getTxtPrice().getText());
                    } catch (Exception ex) {
                        error+="Price is not right\n";
                    }
                    
                    MeasurementUnit unit = (MeasurementUnit) panel.getCmbUnit().getSelectedItem();
                    
                    ProductType type = (ProductType) panel.getCmbType().getSelectedItem();
                    User user = MainCoordinator.getInstance().getUser();
                    
                    if(error!="") throw new Exception(error);
                    Product product = new Product(price, unit, name, type, user);
                    Communcation.getInstance().addProduct(product);
                    
                    JOptionPane.showMessageDialog(panel, "Product "+name+" added", "Added", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }
        });
    }

}
