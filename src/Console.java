
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sohan
 */
public class Console {
    
}

class SpamCheck{
    public static final String BLACKHOLE = "sbl.spamhaus.org";

    public static void main(String[] args) throws UnknownHostException {
        for (String arg : args) {
            if (isSpammer(arg)) {
                System.out.println(arg + " is a known spammer.");
            } else {
                System.out.println(arg + " appears legitimate.");
            }
        }
    }
    private static boolean isSpammer(String arg) {
        try {
            InetAddress address = InetAddress.getByName(arg);
            byte[] quad = address.getAddress();
            String query = BLACKHOLE;
            for (byte octet : quad) {
                int unsignedByte = octet < 0 ? octet + 256 : octet;
                query = unsignedByte + "." + query;
            }
            InetAddress.getByName(query);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }
}

class LogProcessor{
    public static void main(String[] args) throws FileNotFoundException, IOException{
        FileInputStream fin = new FileInputStream(args[0]);
        Reader in = new InputStreamReader(fin);
        BufferedReader bin = new BufferedReader(in);
        
        for(String entry = bin.readLine();
            entry!=null;
            entry = bin.readLine()){
            //seperate out thr ip address
            int index = entry.indexOf(' ');
            String ip=entry.substring(0,index);
            String theRest = entry.substring(index);
            
            //Ask DNS for the hostname and print it out
            InetAddress address = InetAddress.getByName(ip);
            System.out.println(address.getHostName()+theRest);
        }
    }
}
