/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller.invoice;

import communcation.Communcation;
import domain.Article;
import domain.Invoice;
import domain.InvoiceItem;
import domain.Product;
import domain.Projection;
import domain.enums.MeasurementUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import view.coordinator.MainCoordinator;
import view.model.table.InvoiceItemTableModel;
import view.panel.invoice.PanelInvoiceAdd;

/**
 *
 * @author user
 */
public class ControllerInvoiceAdd {

    PanelInvoiceAdd panel;
    InvoiceItemTableModel model;

    public ControllerInvoiceAdd(PanelInvoiceAdd panel) {
        this.panel = panel;
        model = new InvoiceItemTableModel(new Invoice());
        panel.getTableInvoiceItem().setModel(model);
    }

    public void openPanel() {
        panel.setVisible(true);
        setExitButton();
        fillPanel();
        setListeners();
    }

    public void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelInvoiceAdd getPanel() {
        return panel;
    }

    private void fillPanel() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        String datum = sdf.format(new Date());
        panel.getTxtnDate().setText(datum);

        try {
            ArrayList<Product> products = Communcation.getInstance().getAllProducts();
            for (Product product : products) {
                panel.getCmbProduct().addItem(product);
            }
            panel.getCmbProduct().setSelectedIndex(-1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, "Can't load product table", "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {
            ArrayList<Projection> projections = Communcation.getInstance().getAllProjections();
            for (Projection projection : projections) {
                if (projection.getStartDate().after(new Date())) {
                    panel.getCmbProjection().addItem(projection);
                }
            }
            panel.getCmbProjection().setSelectedIndex(-1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, "Can't load projection table", "Error", JOptionPane.ERROR_MESSAGE);
        }

        setColumnSizes();
    }

    public void setColumnSizes() {
        TableColumnModel columnModel = panel.getTableInvoiceItem().getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(5);
        columnModel.getColumn(1).setPreferredWidth(250);
        columnModel.getColumn(2).setPreferredWidth(5);
        columnModel.getColumn(3).setPreferredWidth(5);
        columnModel.getColumn(4).setPreferredWidth(5);
        columnModel.getColumn(5).setPreferredWidth(5);
    }

    private void setListeners() {
        setProductListener();
        setProjectionListener();
        setInvoiceItemRemove();
        setSaveListener();
    }

    private void setProductListener() {
        panel.getCmbProduct().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = (Product) panel.getCmbProduct().getSelectedItem();
                if (product != null) {
                    panel.getTxtPriceProduct().setText(product.getPrice().toString());
                    panel.getTxtQuantityProduct().setText("1");
                    panel.getTxtQuantityProduct().grabFocus();
                    panel.getTxtQuantityProduct().setSelectionStart(0);
                }
            }
        });

        panel.getBtnAddProduct().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Invoice invoice = model.getInvoice();
                int orderNumber = model.getInvoice().getItems().size() + 1;
                BigDecimal price = new BigDecimal(panel.getTxtPriceProduct().getText());
                int quantity = Integer.parseInt(panel.getTxtQuantityProduct().getText());
                Article article = (Product) panel.getCmbProduct().getSelectedItem();
                MeasurementUnit unit = article.getUnit();
                BigDecimal total = price.multiply(new BigDecimal(quantity));

                InvoiceItem item = new InvoiceItem(invoice, orderNumber, price, quantity, unit, article, total);
                model.add(item);
                panel.getLblTotal().setText(model.getInvoice().getTotal().toString());
            }
        });
    }

    private void setProjectionListener() {
        panel.getCmbProjection().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Projection projection = (Projection) panel.getCmbProjection().getSelectedItem();
                if (projection != null) {
                    panel.getTxtPriceTicket().setText(projection.getPrice().toString());
                    panel.getTxtQuantityTicket().setText("1");
                    panel.getTxtQuantityTicket().grabFocus();
                    panel.getTxtQuantityTicket().setSelectionStart(0);
                }
            }
        });

        panel.getBtnAddTicket().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    BigDecimal price = new BigDecimal(panel.getTxtPriceTicket().getText());
                    int quantity = Integer.parseInt(panel.getTxtQuantityTicket().getText());
                    Projection projection = (Projection) panel.getCmbProjection().getSelectedItem();
                    BigDecimal total = price.multiply(new BigDecimal(quantity));

                    model.addTicket(price, quantity, projection, total);
                    panel.getLblTotal().setText(model.getInvoice().getTotal().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    panel.getLblTotal().setText(model.getInvoice().getTotal().toString());
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
    }

    private void setInvoiceItemRemove() {
        panel.getBtnRemoveInvoiceItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = panel.getTableInvoiceItem().getSelectedRow();
                if (index >= 0) {
                    int number = JOptionPane.showConfirmDialog(panel, "Are you sure you what to delete invoice item", "Delete", 0);
                    if (number == 0) {
                        model.remove(index);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Not selected", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setSaveListener() {
        panel.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Invoice invoice = model.getInvoice();

                    String number = panel.getTxtNumber().getText();
                    if (number.isEmpty()) {
                        throw new Exception("Number can't be empty");
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                    Date date = null;
                    try {
                        date = sdf.parse(panel.getTxtnDate().getText());
                        invoice.setDate(date);
                    } catch (ParseException ex) {
                        throw ex;
                    }

                    invoice.setDate(date);
                    invoice.setNumber(number);
                    invoice.setUser(MainCoordinator.getInstance().getUser());

                    Communcation.getInstance().addInvoice(invoice);
                    JOptionPane.showMessageDialog(panel, "Invoice added", "Added", JOptionPane.INFORMATION_MESSAGE);
                    
                    clearFields();
                } catch (Exception exc) {
                    exc.printStackTrace();
                    JOptionPane.showMessageDialog(panel, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

            private void clearFields() {
                model = new InvoiceItemTableModel(new Invoice());
                panel.getTableInvoiceItem().setModel(model);
                model.setMap(new HashMap<>());

                panel.getCmbProduct().setSelectedItem(-1);
                panel.getCmbProjection().setSelectedItem(-1);
                panel.getTxtNumber().setText("");
                panel.getTxtQuantityProduct().setText("");
                panel.getTxtQuantityTicket().setText("");
                panel.getTxtPriceProduct().setText("");
                panel.getTxtPriceTicket().setText("");
                panel.getLblTotal().setText("0");
                setColumnSizes();
            }
        });
    }

}
