/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartx.du;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author samiul
 */
class testobject implements Serializable {

    HashMap<Integer, data> hhmm = new HashMap<>();

    public testobject(HashMap<Integer, data> hm) {
        hhmm = hm;
    }
}

class data implements Serializable {

    int roll;
    
    String regnum;
    String session;
    String department;
    String name;
    String father;
    String mother;
    String dob;
    String username;
    String password;
    String hall;
    String year;
    String semester;
    
    String email;
    String mobile;
    
    
    String residentialStatus;
    String roomNumber;
    
    String maj1;
    double maj1_attendance;
    String maj2;
    double maj2_attendance;
    String maj3;
    double maj3_attendance;
    String min1;
    double min1_attendance;
    String min2;
    double min2_attendance;
    String min3;
    double min3_attendance;
    boolean hallDuePaid = false;
    boolean eligibility = false;
    
    int transactionID;
    
    boolean paymentClear;
}

public class MyHashMap {

}
