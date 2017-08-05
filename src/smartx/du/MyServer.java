/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartx.du;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static smartx.du.Server.mmm;

class RT extends Thread {

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(2002);
            Socket s = ss.accept();
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            data d = (data) ois.readObject();

            int n = d.roll;

            // data hd = mmm.get(n);
            // if(hd.regnum.equals(d.regnum) && hd.dob.equals(d.dob))
            mmm.put(n, d);
            //else System.out.println("Not Authentic");
            String path = "/home/saif_m_dhrubo/SmartX Du/StudentInfo/" + String.valueOf(n) + ".txt";
            System.out.println(path);
            File f = new File(path);

            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(SignUpWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

            FileWriter writer;
            try {
                writer = new FileWriter(f);
                writer.write(d.regnum + "\n" + d.session + "\n" + d.department + "\n" + d.name + "\n" + d.father + "\n" + d.mother + "\n" + d.dob + "\n" + d.password + "\n" + d.hall + "\n" + d.year + "\n" + d.semester + "\n" + d.maj1 + "\n" + d.maj1_attendance + "\n" + d.maj2 + "\n" + d.maj2_attendance + "\n" + d.maj3 + "\n" + d.maj3_attendance + "\n" + d.min1 + "\n" + d.min1_attendance + "\n" + d.min2 + "\n" + d.min2_attendance + "\n" + d.min3 + "\n" + d.min3_attendance + "\n" + d.hallDuePaid + "\n" + d.eligibility + "\n" + d.transactionID + "\n" + d.paymentClear);
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(SignUpWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Received");

            is.close();
            s.close();
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class RHT extends Thread {

    int sock;

    public RHT(int sock) {
        this.sock = sock;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(sock);
            Socket s = ss.accept();
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            testobject to = (testobject) ois.readObject();

            mmm = to.hhmm;
            System.out.println("Received HashMap");

            is.close();
            s.close();
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {

        }

    }
}

class ST extends Thread {

    int sock;

    public ST(int sock) {
        this.sock = sock;
    }

    public void run(HashMap<Integer, data> h) {

        try {
            testobject to = new testobject(h);
            ServerSocket ss = new ServerSocket(sock);
            Socket s = ss.accept();
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeObject(to);

            System.out.println("Sent");
            oos.close();
            os.close();
            s.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void start(HashMap<Integer, data> h) {
        this.run(h);
    }

}

class Server {

    public static HashMap<Integer, data> mmm = new HashMap<>();
    RT r1 = new RT();
    RT r2 = new RT();
    RT r3 = new RT();
    RT r4 = new RT();
    RT r5 = new RT();

    RHT rh1 = new RHT(2002);
    RHT rh2 = new RHT(2003);
    RHT rh3 = new RHT(2014);

    ST s1 = new ST(2005);
    ST s2 = new ST(2006);
    ST s3 = new ST(2007);
    ST s4 = new ST(2008);

}

public class MyServer {

    public static void main(String args[]) throws InterruptedException {
        Server ser = new Server();

//        data d1 = new data();
//        data d2 = new data();
//        d1.dob = "11/11/1111";
//            d2.dob = "22/22/2222";
//         d1.regnum = "1111-111-111";
//          d2.regnum = "2222-222-222";
//          
//        ser.mmm.put(1, d1);
//        ser.mmm.put(2, d2);
//get student data
        ser.r1.start();
        ser.r1.join();
        ser.r2.start();
        ser.r2.join();

//send to dept
        ser.s1.start(ser.mmm);
        ser.s1.join();
//receive from dept
        ser.rh1.start();
        ser.rh1.join();
//send to hall
        ser.s2.start(ser.mmm);
        ser.s2.join();
//receive from hall
        ser.rh2.start();
        ser.rh2.join();
//send to examoffice
        ser.s3.start(ser.mmm);
        ser.s3.join();
//receive from examoffice
        ser.rh3.start();
        ser.rh3.join();
//send to dept finally
        ser.s4.start(ser.mmm);

    }

}
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */ 

//package smartx.du;
//import java.net.*;
//import java.io.*;
//import java.util.HashMap;
//import java.util.Map;
//class testobject implements Serializable {
//    HashMap<Integer, data> hhmm = new HashMap<>();
//public  testobject(HashMap<Integer,data> hm ){
//hhmm = hm;
//}
//}
//
//public class SimpleServer  {
//       public static HashMap<Integer, data> mmm = new HashMap<>();
//       
//public static void main(String args[]) {
//int port = 2002;
//try {
//ServerSocket ss = new ServerSocket(port);
//Socket s = ss.accept();
//InputStream is = s.getInputStream();
//ObjectInputStream ois = new ObjectInputStream(is);
//testobject to = (testobject)ois.readObject();
//
//     mmm =  to.hhmm;
//    for (Map.Entry<Integer, data> en : mmm.entrySet()) {
//         Integer key = (int) en.getKey();
//        data value = (data) en.getValue();
//        System.out.println(key + "  " + value.name);
//        
//    }
//
//
//
//is.close();
//s.close();
//ss.close();
//}
//
//catch(Exception e){System.out.println(e);}
//}
//}
