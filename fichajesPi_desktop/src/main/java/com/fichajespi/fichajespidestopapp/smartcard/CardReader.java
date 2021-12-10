/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fichajespi.fichajespidestopapp.smartcard;

import com.fichajespi.fichajespidestopapp.MainWindow;
import com.fichajespi.fichajespidestopapp.entity.Fichaje;
import com.fichajespi.fichajespidestopapp.httpClient.RequestSender;
import java.awt.Robot;
import java.awt.event.InputEvent;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

/**
 *
 * @author alex
 */
public class CardReader extends Thread {

  private MainWindow instance;
  private RequestSender rs;

  public CardReader(MainWindow instance) {
    this.instance = instance;
    this.rs = new RequestSender();
  }

  @Override
  public void run() {
    while (true) {

      try {
        // Display the list of terminals
        TerminalFactory factory = TerminalFactory.getDefault();
        List<CardTerminal> terminals = factory.terminals().list();
        //System.out.println("Terminals: " + terminals);

        // Use the first terminal
        CardTerminal terminal = terminals.get(0);

        // Connect wit hthe card
        if (terminal.isCardPresent()) {

          
          //Simulamos un click de ratón para despertar la pantalla si se ha apagado
          Robot robot = new Robot();
          robot.mousePress(InputEvent.BUTTON1_MASK);
          robot.mouseRelease(InputEvent.BUTTON1_MASK);

          Card card = terminal.connect("*");
          //System.out.println("Card: " + card);
          CardChannel channel = card.getBasicChannel();

          // Send test command
          ResponseAPDU response = channel.transmit(new CommandAPDU(new byte[]{
            (byte) 0xFF,
            (byte) 0xCA,
            (byte) 0x00,
            (byte) 0x00,
            (byte) 0x00}));
          //System.out.println("Response: " + response.toString());

          if (response.getSW1() == 0x63 && response.getSW2() == 0x00) {
            //System.out.println("Failed");
          } else {
            //System.out.println("UID: " + bin2hex(response.getData()));
            BigInteger decimal = new BigInteger(bin2hex(response.getData()), 16);
            System.out.println(decimal);
            fichar(decimal.toString());
            // Disconnect the card
            card.disconnect(false);
          }

        }

      } catch (Exception e) {
        //System.out.println("Ouch: " + e.toString());

      }
    }
  }

  private void fichar(String number) throws InterruptedException, IOException {
    Fichaje fichaje = rs.sendRequest(number);
    if (fichaje != null) {
      System.out.println("Fichaje OK");
      instance.changeNumero(number);
      instance.changeNombre(fichaje.getNombreUsuario());
      StringBuilder builder = new StringBuilder();
      builder.append("Hora de ");
      builder.append(fichaje.getTipo());
      builder.append(": ");
      //DateTimeFormatter formatterIn = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
      //LocalDateTime dateTime = LocalDateTime.parse(fichaje.getTime(), formatterIn);
      LocalTime dateTime = LocalTime.parse(fichaje.getHora());
      DateTimeFormatter formatterOut = DateTimeFormatter.ofPattern("HH:mm:ss");
      builder.append(dateTime.format(formatterOut));
      instance.changeFichaje(builder.toString());

      CardReader.sleep(3000);
    } else {
      instance.changeNumero(number + " no existe");
      CardReader.sleep(5000);

    }

    instance.resetScreen();
  }

  private String bin2hex(byte[] data) {
    return String.format("%0" + (data.length * 2) + "X", new BigInteger(1, data));
  }

}
