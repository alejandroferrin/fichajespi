/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fichajespi.fichajespidestopapp.tools;

import com.fichajespi.fichajespidestopapp.MainWindow;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class CurrentDate extends Thread {

	private MainWindow instance;

	public CurrentDate(MainWindow instance) {
		this.instance = instance;
	}

	@Override
	public void run() {
		while (true) {
			try {
				LocalDate date = LocalDate.now(); // Gets the current time
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
				instance.changeDate(date.format(formatter));
				this.sleep(1000);  // interval given in milliseconds  
			} catch (InterruptedException ex) {
				Logger.getLogger(CurrentDate.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

}
