/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.session;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aleks
 */
public class Session {

    private static Session instance;
    private Map<String, Object> mySession;

    private Session() {
        mySession = new HashMap<String, Object>();
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Object getValue(String key) {
        if (mySession.containsKey(key)) {
            return mySession.get(key);
        }
        return null;
    }

    public void addItem(String key, Object value) {
        mySession.put(key, value);
    }

    public void removeItem(String key) {
        if (mySession.containsKey(key)) {
            mySession.remove(key);
        }
    }
}
