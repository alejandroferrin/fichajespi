/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fichajespi.fichajespidestopapp.tools;

import com.fichajespi.fichajespidestopapp.MainWindow;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class CurrentTime extends Thread {

    private MainWindow instance;

    public CurrentTime(MainWindow instance) {
        this.instance = instance;
    }

    @Override
    public void run() {
        while (true) {
            try {
                LocalTime time = LocalTime.now(); // Gets the current time
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                instance.changeTime(time.format(formatter));
                this.sleep(1000);  // interval given in milliseconds  
            } catch (InterruptedException ex) {
                Logger.getLogger(CurrentTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
