/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller.invoice;

import communcation.Communcation;
import domain.Invoice;
import domain.InvoiceItem;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
    
    public void openPanel(){
        panel.setVisible(true);
        setExitButton();
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
            for (Invoice invoice : invoices) {
                for (InvoiceItem item : invoice.getItems()) {
                    System.out.println(item.getArticle());
                }
                System.out.println("");
            }
            model = new InvoiceTableModel(invoices);
            panel.getTableInvoice().setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Table can't load", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
