/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import psproject_v5.communication.Communication;
import psproject_v5.ui.form.FrmEmployee;
import psproject_v5.ui.form.FrmMain;
import psproject_v5.communication.Receiver;
import psproject_v5.communication.Sender;

/**
 *
 * @author aleks
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
//            Socket socket = new Socket("127.0.0.1", 9000);
//            Sender sender = new Sender(socket);
//            Receiver receiver = new Receiver(socket);
            Communication.getInstance();
            System.out.println("Upravo povezano i kreirana komunikacija");
            FrmMain frmMain = new FrmMain();
            frmMain.setVisible(true);

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
