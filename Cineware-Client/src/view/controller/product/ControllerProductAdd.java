/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller.product;

import view.panel.mode.ProductMode;
import view.panel.product.PanelProductAdd;

/**
 *
 * @author user
 */
public class ControllerProductAdd {
    PanelProductAdd panel;
    ProductMode mode;
    
    public ControllerProductAdd(PanelProductAdd panel, ProductMode mode) {
        this.panel = panel;
        this.mode = mode;
    }
    
    public void openPanel(){
        panel.setVisible(true);
        setExitButton();
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelProductAdd getPanel() {
        return panel;
    }
    
    
}
