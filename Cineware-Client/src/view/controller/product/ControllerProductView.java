/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller.product;

import view.panel.product.PanelProductView;

/**
 *
 * @author user
 */
public class ControllerProductView {
    PanelProductView panel;

    public ControllerProductView(PanelProductView panel) {
        this.panel = panel;
    }
    
    public void openPanel(){
        panel.setVisible(true);
        setExitButton();
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelProductView getPanel() {
        return panel;
    }
    
    
}
