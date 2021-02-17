/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller.invoice;

import communcation.Communcation;
import domain.Invoice;
import domain.InvoiceItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.coordinator.MainCoordinator;
import view.model.table.InvoiceTableModel;
import view.panel.invoice.PanelInvoiceView;

/**
 *
 * @author user
 */
public class ControllerInvoiceView {

    PanelInvoiceView panel;
    InvoiceTableModel model;

    public ControllerInvoiceView(PanelInvoiceView panel) {
        this.panel = panel;
        fillTable();
    }

    public void openPanel() {
        panel.setVisible(true);
        setExitButton();
        setListeners();
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelInvoiceView getPanel() {
        return panel;
    }

    private void fillTable() {
        try {
            ArrayList<Invoice> invoices = Communcation.getInstance().getAllInvoices();
            model = new InvoiceTableModel(invoices);
            panel.getTableInvoice().setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Table can't load", "Error", JOptionPane.ERROR_MESSAGE);
        }
        fillTotal();
        
    }
    
    public void fillTotal(){
        BigDecimal total = model.getTotal();
        panel.getLblTotal().setText(total.toString());
    }

    private void setListeners() {
        setSearchListener();
        setStornoListener();
    }

    private void setSearchListener() {
        panel.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sort = panel.getTxtSearch().getText();
                model.setSortValue(sort);
                model.sort();
                fillTotal();
            }
        });
    }

    private void setStornoListener() {
        panel.getBtnStorno().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = panel.getTableInvoice().getSelectedRow();
                if(index>=0){
                    
                    try {
                        Invoice invoice = model.getInverted(index);
                        invoice.setUser(MainCoordinator.getInstance().getUser());
                        Communcation.getInstance().stornoInvoice(invoice);
                        model.refresh();
                        fillTotal();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, "Invoice not storned\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(ControllerInvoiceView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(panel, "Invoice not selected", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
