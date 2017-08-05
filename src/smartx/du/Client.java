package smartx.du;

import java.net.*;
import java.io.*;
import java.util.HashMap;

class SimpleClient {

    String ip = "192.168.19.115";

    void send(data d) {
        try {
            Socket s = new Socket("localhost", 2002);
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            System.out.println("Student Data Sent to Server");
            oos.writeObject(d);
            oos.close();
            os.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void sendh(HashMap<Integer, data> d, int sock) {
        try {
            Socket s = new Socket(ip, sock);
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            testobject to = new testobject(d);
            oos.writeObject(to);
            System.out.println("Student Hashmap Sent to Server");
            oos.close();
            os.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    HashMap<Integer, data> receive(int sock) {
        try {
            Socket s = new Socket(ip, sock);
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            testobject to = (testobject) ois.readObject();
            System.out.println("Student hashmap received from Server");
            is.close();
            s.close();
            s.close();
            return to.hhmm;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

}
