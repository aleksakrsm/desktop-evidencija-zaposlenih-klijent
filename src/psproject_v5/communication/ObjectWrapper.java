/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.communication;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aleks
 */
public class ObjectWrapper {
    private Object object;

    public synchronized Object getObject() {
        try {
            if(object==null){
                wait();
            }else{
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        Object o = object;
        object=null;
        return o;
    }

    public synchronized void setObject(Object object) {
        this.object = object;
        notifyAll();
    }
    
}