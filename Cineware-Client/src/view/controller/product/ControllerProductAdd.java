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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import view.constant.Constant;
import view.coordinator.MainCoordinator;
import view.model.table.ProductTableModel;
import view.panel.mode.Mode;
import view.panel.product.PanelProductAdd;

/**
 *
 * @author user
 */
public class ControllerProductAdd {

    PanelProductAdd panel;
    Mode mode;
    ArrayList<Supplier<JComponent>> componentGetters;

    public ControllerProductAdd(PanelProductAdd panel, Mode mode) {
        this.panel = panel;
        this.mode = mode;
        componentGetters = new ArrayList<>();
    }

    public void fillComponentGetters() {
        componentGetters.add(() -> panel.getBtnEdit());
        componentGetters.add(() -> panel.getBtnDelete());
        componentGetters.add(() -> panel.getTxtName());
        componentGetters.add(() -> panel.getTxtPrice());
    }

    public void openPanel() {
        panel.setVisible(true);
        fillTable();
        setExitButton();
        fillComponentGetters();
        setListeners();
        prepareForm();
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

        setAddListener();
        setEnableChangesListener();
        setEditListener();
        setDeleteListener();

    }

    private void prepareForm() {
        switch (mode) {
            case ADD:
                panel.getBtnAdd().setVisible(true);
                panel.getBtnEdit().setVisible(false);
                panel.getBtnDelete().setVisible(false);
                panel.getBtnEnableChanges().setVisible(false);
                break;
            case EDIT:
                panel.getBtnAdd().setVisible(false);
                panel.getBtnEdit().setVisible(true);
                panel.getBtnDelete().setVisible(true);
                panel.getBtnEnableChanges().setVisible(true);
                panel.getBtnEdit().setEnabled(false);
                panel.getBtnDelete().setEnabled(false);
                panel.getBtnEnableChanges().setEnabled(true);
                fillProductDetails();
                disableFields();
                break;
        }
    }

    private void disableFields() {
        for (Supplier<JComponent> componentGetter : componentGetters) {
            componentGetter.get().setEnabled(false);
        }

    }

    private void enableFields() {
        for (Supplier<JComponent> componentGetter : componentGetters) {
            componentGetter.get().setEnabled(true);
        }

    }

    private void fillProductDetails() {
        Product product = (Product) MainCoordinator.getInstance().getParams().get(Constant.PRODUCT_DETAILS);

        panel.getTxtName().setText(product.getName());
        panel.getTxtPrice().setText(product.getPrice().toString());
        panel.getCmbType().setSelectedItem(product.getType());
        panel.getCmbUnit().setSelectedItem(product.getUnit());

    }

    private void setAddListener() {
        panel.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Product product = getProductFromPanel();
                    Communcation.getInstance().addProduct(product);

                    JOptionPane.showMessageDialog(panel, "Product " + product.getName() + " added", "Added", JOptionPane.INFORMATION_MESSAGE);
                    panel.getTxtName().setText("");
                    panel.getTxtPrice().setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }
        });
    }

    private Product getProductFromPanel() throws Exception {
        String error = "";
        String name = panel.getTxtName().getText();
        if (name.isEmpty()) {
            error += "Name can't be empty\n";
        }
        BigDecimal price = null;
        try {
            price = new BigDecimal(panel.getTxtPrice().getText());
        } catch (Exception ex) {
            error += "Price is not right\n";
        }

        MeasurementUnit unit = (MeasurementUnit) panel.getCmbUnit().getSelectedItem();

        ProductType type = (ProductType) panel.getCmbType().getSelectedItem();
        User user = MainCoordinator.getInstance().getUser();

        if (error != "") {
            throw new Exception(error);
        }

        Product product = new Product(price, unit, name, type, user);
        return product;
    }

    private void setEnableChangesListener() {
        panel.getBtnEnableChanges().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableFields();
                panel.getBtnEnableChanges().setEnabled(false);
            }
        });
    }

    private void setEditListener() {
        panel.getBtnEdit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Product product = getProductFromPanel();
                    product.setId(((Product) MainCoordinator.getInstance().getParams().get(Constant.PRODUCT_DETAILS)).getId());
                    Communcation.getInstance().updateProduct(product);

                    JOptionPane.showMessageDialog(panel, "Product " + product.getName() + " updated", "Update", JOptionPane.INFORMATION_MESSAGE);
                    updateTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }

        });
    }

    private void setDeleteListener() {
        panel.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = (Product) MainCoordinator.getInstance().getParams().get(Constant.PRODUCT_DETAILS);
                int number = JOptionPane.showConfirmDialog(panel, "Are you sure you what to delete product " + product, "Delete", 0);
                if (number == 0) {
                    try {
                        Communcation.getInstance().deleteProduct(product);
                        updateTable();
                    } catch (Exception ex) {
                        Logger.getLogger(ControllerProductAdd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                
            }
        });
    }

    private void updateTable() throws Exception {
        ProductTableModel model = (ProductTableModel) MainCoordinator.getInstance().getParams().get(Constant.PRODUCT_TABLE_MODEL);
        model.refresh();
        panel.getExitButton1().doClick();
    }

}
