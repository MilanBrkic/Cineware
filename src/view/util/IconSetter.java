/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.util;

import java.awt.Window;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author Brka
 */
public class IconSetter {
    Window frame;

    public IconSetter(Window frame) {
        this.frame = frame;
    }
    
    public void setIcon() {
        String basePath = new File("").getAbsolutePath();
        String iconPath = basePath + "\\resources\\cineware-icon.png";

        ImageIcon img = new ImageIcon(iconPath);
        frame.setIconImage(img.getImage());

    }
    
    
}
