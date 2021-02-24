/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.util;

import javax.swing.JLabel;

/**
 *
 * @author user
 */
public class Dots extends Thread {

    JLabel label;

    public Dots(JLabel label) {
        this.label = label;
        start();
    }

    @Override
    public void run() {
        String text = "Server is running";
        label.setText(text);
        int broj = 0;
        try {
            while (true) {
                if (broj % 3 == 0 && broj != 0) {
                    text = text.substring(0, text.length() - 3);
                    label.setText(text);
                    sleep(800);

                }
                text += ".";
                label.setText(text);
                broj++;
                sleep(1000);

            }
        } catch (InterruptedException e) {

        }

    }

}
