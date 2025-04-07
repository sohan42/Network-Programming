import java.net.*;
import java.util.*;

class Example{
    //Program to get host name and IP address
    void byName(){
        try { 
            InetAddress address = InetAddress.getByName("www.google.com");
            System.out.println("Host Name: "+address.getHostName());
            System.out.println("IP Address: "+address.getHostAddress());
        } catch (UnknownHostException ex) {
            System.err.println("Unable to resolve hostname "+ex.getMessage());
        }
    }
    
    //Program to get host name and all its IP addresses
    void allByName(){
        try {
            InetAddress[] address = InetAddress.getAllByName("www.nepal.com");
            for(InetAddress a: address){
                System.out.println("Host Name: "+a.getHostName());
                System.out.println("IP Address: "+a.getHostAddress());
            }
        } catch (UnknownHostException ex) {
            System.err.println("Unable to resolve hostname "+ex.getMessage());
        }
    }
    
    //Program to get host name and IP address of local machine 
    void localHost(){
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("Host Name: "+address.getHostName());
            System.out.println("IP Address: "+address.getHostAddress());
        } catch (UnknownHostException ex) {
            System.err.println("Unable to resolve hostname "+ex.getMessage());
        }
    }
    
    //Program to get host address of the given IP address 
    void hostName(){
        try {
            InetAddress address = InetAddress.getByName("142.250.100.6");
            System.out.println(address.getCanonicalHostName());
        } catch (UnknownHostException ex) {
            System.err.println("Unable to resolve hostname "+ex.getMessage());
        }
    } 
}

class Address{
    void check() throws UnknownHostException{
        InetAddress a1 = InetAddress.getByName("0.0.0.0");
        InetAddress a2 = InetAddress.getByName("127.0.0.1");
        InetAddress a3 = InetAddress.getByName("fe80::1");
        InetAddress a4 = InetAddress.getByName("10.0.0.1");
        InetAddress a5 = InetAddress.getByName("ff0e::1");
        
        checkAddress(a1);
        checkAddress(a2);
        checkAddress(a3);
        checkAddress(a4);
        checkAddress(a5);
    }
    
    private void checkAddress(InetAddress address){ 
        System.out.println("Address: "+address.getHostAddress() );
        System.out.println("is any local address: "+address.isAnyLocalAddress());
        System.out.println("is loop back address: "+address.isLoopbackAddress());
        System.out.println("is link local address: "+address.isLinkLocalAddress());
        System.out.println("is site local address: "+address.isSiteLocalAddress());
        System.out.println("is multicat address: "+address.isMulticastAddress());
    
        if(address.isMulticastAddress()){
            System.out.println("is mulitcast global: "+address.isMCGlobal());
            System.out.println("is mulitcast node local: "+address.isMCNodeLocal());
            System.out.println("is mulitcast link local: "+address.isMCLinkLocal());
            System.out.println("is mulitcast site local: "+address.isMCSiteLocal());
            System.out.println("is mulitcast Organization Local: "+address.isMCOrgLocal());
        }
        System.out.println();
    }
    
    //WAP to address is reachable or not
    void Reachable() throws Exception{
        String s;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Host name(url): ");
        s = sc.next();
        
        InetAddress a = InetAddress.getByName(s);
        
        int timeout = 5000; //timeout in 5 sec
        
        boolean isReachable = a.isReachable(timeout);
        
        if(isReachable){
            System.out.println("Host: "+a.getHostAddress()+" is reachable!");
        }
        else{
            System.out.println("Host: "+a.getHostAddress()+" is not reachable!");
        }
    } 
}

class IPv4Compitable{
    void compitableTset() throws UnknownHostException{
        InetAddress address = InetAddress.getByName("::192.168.1.1");
        
        //Check if it is IPv6 address
        if(address instanceof Inet6Address){
            Inet6Address Addresss6 = (Inet6Address) address; //casting into Inet6Address
        
            boolean IPv4Comp = Addresss6.isIPv4CompatibleAddress();
            System.out.println("Is IPv4-Compitable? "+IPv4Comp);
            }
            else{
                System.out.println("Not an IPv6 address.");
        }
    }
}

class MyInterface{
    //Program to check primary ethernet interface.
    void checkByName() throws SocketException{
        NetworkInterface eth0 = NetworkInterface.getByName("wireless_32768");
        if(eth0!=null){
            System.out.println("Found Ethernet Interface: "+eth0.getDisplayName());
        }else{
            System.out.println("Enternet Interface not found!");
        }
    }
    //Program to find the network interface for the local address.
    void checkByAddress() throws UnknownHostException, SocketException{
        InetAddress ip = InetAddress.getByName("192.168.1.4");
        NetworkInterface ni = NetworkInterface.getByInetAddress(ip);
        
        if(ni!=null){
            System.out.println("IP "+ ip.getHostAddress()+" is on interface: "+ni.getName());
        }else{
            System.out.println("IP not foundon any local interface!");
        }
    }
    //Program to list all network interface on localhost.
    void allInterface() throws SocketException{
        //Get all interface
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        
        while(interfaces.hasMoreElements()){
            NetworkInterface ni = interfaces.nextElement();
            System.out.println("Interface: "+ni.getName());
            
            //Get IP addresses for the interface
            Enumeration<InetAddress> addresses = ni.getInetAddresses();
            while(addresses.hasMoreElements()){
                System.out.println("Address: "+addresses.nextElement());
                System.out.println("------------------------");
            }
        }
    }
}

public class MyNet {
    public static void main(String[] args) throws Exception {
     MyInterface mi = new MyInterface();
     mi.allInterface();
    }
}