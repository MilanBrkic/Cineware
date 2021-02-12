/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller.projection;

import view.panel.projection.PanelProjectionView;

/**
 *
 * @author user
 */
public class ControllerProjectionView {
    PanelProjectionView panel;

    public ControllerProjectionView(PanelProjectionView panel) {
        this.panel = panel;
    }
    
    public void openPanel(){
        panel.setVisible(true);
        setExitButton();
    }

    private void setExitButton() {
        panel.getExitButton1().setPanel(panel);
    }

    public PanelProjectionView getPanel() {
        return panel;
    }
    
    
}
