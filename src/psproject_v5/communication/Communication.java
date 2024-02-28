/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.communication;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import psproject_v5.communication.Receiver;
import psproject_v5.communication.Sender;

/**
 *
 * @author aleks
 */
public class Communication {

    private static Communication instance;
    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    private Communication() throws IOException {
        try {
            socket = new Socket("127.0.0.1",9000);
            sender = new Sender(socket);
            receiver = new Receiver(socket);
            System.out.println("NOVA INSTANCA KOMUNIKACIJE");
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public static Communication getInstance() throws IOException {
//            System.out.println((instance==null)? "nije napravljena":"jeste");
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public Sender getSender() {
        return sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public Socket getSocket() {
        return socket;
    }

//    public void setSocket(Socket socket) {
//        this.socket = socket;
//    }
    

}
