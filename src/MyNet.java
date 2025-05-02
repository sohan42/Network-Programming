import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.swing.*;

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
            System.out.println("IP not found on any local interface!");
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

class URLExample{
    void URLFromString(){
        try {
            URL url = new URL("https://example.com:443/index.html?user=admin#section1");
            
            System.out.println("Protocal: "+url.getProtocol());
            System.out.println("Host: "+url.getHost());
            System.out.println("Port: "+url.getPort());
            System.out.println("File: "+url.getFile());
            System.out.println("Path: "+url.getPath());
            System.out.println("Query: "+url.getQuery());
            System.out.println("Refrence: "+url.getRef());
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    void URLFromComponents(){
        try {
            //Using protocal, host, file
            URL url1 = new URL("https","www.example.com","/index.html");
            
            //using protocal, host, port, file
            URL url2 = new URL("https","www.example.com",443,"/index.html");
            
            //Using a base URL to resolve relative path
            URL baseURL = new URL("https://www.example.com/docs/");
            URL url3 = new URL(baseURL,"guide.html");
            
            // Display the full URLs
            System.out.println("URL1: " + url1.toExternalForm());
            System.out.println("URL2: " + url2.toExternalForm());
            System.out.println("URL3: " + url3.toExternalForm());

        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //Program to demonstrate retrieving data from a URL.
    void urlDataRetrive(){
        try {
            URL url = new URL("https://example.com/index.html");
            try{
                InputStream ips = url.openStream(); //Open connection
                //Read content
                BufferedReader reader = new BufferedReader(new InputStreamReader(ips));
                String line;
                while((line=reader.readLine())!=null){
                    System.out.println(line);
                }       
            }catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Program to check whether two URL point to the same file.
    void urlComparision(){
        try {
            //Creating two url object that might refer to the same file
            URL url1 = new URL("https://example.com/index.html");
            URL url2 = new URL("https","example.com",443,"/index.html");
            
            //compare
            if(url1.sameFile(url2)){
                System.out.println("Both URLs point to the same file!");
            }
            else{
                System.out.println("URLs point to the different resources!");
            }
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

class URIExample{
    //Example: Program to demonstrate constructing a URI from a string.
    void URICreation(){
        try {
            String uriString = "https://example.com/index.html?user=admin#section1";
            URI uri =new URI(uriString);
            
            System.out.println("Constructed URI: "+uri);
            System.out.println("Scheme: "+uri.getScheme());
            System.out.println("Host: "+uri.getHost());
            System.out.println("Path: "+uri.getPath());
            System.out.println("Query: "+uri.getQuery());
            System.out.println("Fragment: "+uri.getFragment());
        } catch (URISyntaxException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Example: Program to demonstrate creating URL from individual components.
    void URIFromCoponents(){
        try {
            URI uri =new URI("https","example.com","/index.html","user=admin","section1");
            System.out.println("Constructed URI: "+uri);
        } catch (URISyntaxException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Example: Program to construct a relative URI and resolving it with base URI.
    void URIResolve(){
        try { 
            URI baseUri = new URI("https://example.com/app/");
            URI relativeUri = new URI("docs/guide.html");
            
            URI resolvedUri = baseUri.resolve(relativeUri);
            System.out.println("Resolved URI: "+resolvedUri);
        } catch (URISyntaxException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Example: Program to perform Semantic Comparison of two URIs with Case Normalization.
    void URICompare(){
        try {
            URI uri1 = new URI("https://Example.com/index.html");
            URI uri2 = new URI("https://example.com/docs/../index.html");
            
            boolean isEqual = uri1.normalize().toString().toLowerCase().equals(uri2.normalize().toString().toLowerCase());
            System.out.println("Semantically equal: "+isEqual);
        } catch (URISyntaxException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
    void URLEncode(){
        try {
            String name = URLEncoder.encode("Raju Thapa",StandardCharsets.UTF_8.toString());
            String message = URLEncoder.encode("Keep Learning!?#",StandardCharsets.UTF_8.toString());
            
            String encodedData = "name: " + name + "&Message: "+message;
            System.out.println("Encoded data: "+encodedData);
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    void URLDecode(){
        String encodedData = "name=Raju+Thapa&message=Keep+going%21";
        try {
            String decodedData = URLDecoder.decode(encodedData, StandardCharsets.UTF_8.toString());
            System.out.println("Decoded Data: "+decodedData);
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Example: Program to demonstrate proxy configuration using different properties.
    void proxyConfiguration(){
        //setup http proxy properties
        System.setProperty("http.proxyHost", "proxy.example.com");
        System.setProperty("http.proxyPort", "8080");
        System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1");
        
        //setup https proxy properties
        System.setProperty("https.proxyHost","scureproxy.example.com");
        System.setProperty("https.proxyPort","8443");
        
        // Example: Retrieve and print the configured proxy properties
        System.out.println("HTTP Proxy: " + System.getProperty("http.proxyHost") + 
                ":" + System.getProperty("http.proxyPort"));
        System.out.println("Non-proxy Hosts: " + System.getProperty("http.nonProxyHosts"));
        System.out.println("HTTPS Proxy: " + System.getProperty("https.proxyHost") + 
                ":" + System.getProperty("https.proxyPort"));
    
    }
   // Example: Program to create a Proxy by specifying its type and the address of the proxy server. 
    void proxyExample(){
        // Create a socket address for the proxy server
        SocketAddress proxyAddress = new InetSocketAddress("proxy.example.com", 8080);
        // Create a Proxy instance with type HTTP
        Proxy httpProxy = new Proxy(Proxy.Type.HTTP, proxyAddress);
        
        try {
            // Use the proxy when opening a URL connection
            URL url = new URL("http://www.example.com");
            URLConnection connection = url.openConnection(httpProxy);
            // Read or process the connection as needed...
            System.out.println("Connected using proxy: " + httpProxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Example: Program to demonstrate Custom Proxy Selector.
    void proxySelectorExample(){
        try{
        // Create a URI for which proxy should be selected
        URI uri = new URI("http://example.com");

        // Create a custom ProxySelector
        ProxySelector mySelector = new ProxySelector() {

            @Override
            public List<Proxy> select(URI uri) {
                System.out.println("Selecting proxy for: " + uri);
                // Use proxy for all connections
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8080));
                return Arrays.asList(proxy);
            }

            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                System.out.println("Connection failed to: " + uri);
            }
        };
        // Set the custom selector as default
        ProxySelector.setDefault(mySelector);
        // Use the selector to get proxy for a URI
        List<Proxy> proxies = ProxySelector.getDefault().select(uri);
        // Print selected proxies
        for (Proxy proxy : proxies) {
            System.out.println("Selected proxy: " + proxy);
        }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    //Example: Program that illustrates how to make a GET request to a server-side program.
    void getExample(){
                try{
                URL url = new URL("https://example.com/index.html?user=admin#section1");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");     // Set method to GET
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
                );
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                in.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
    }
}    
    
class MyAuthentication{
    //Example: Program to demonstrate how to access a password-protected website.
    void protectedSiteAccess(){
        final String username = "user";
        final String password = "passwd";
        //set the default Authenticator
        Authenticator.setDefault(new Authenticator(){
        @Override
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication (username,password.toCharArray());
        }
        });
        try{
            URL url = new URL("https://httpbin.org/basic-auth/user/passwd");
            URLConnection conn = url.openConnection();
            
            //Read and display the content
            try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /*In main:        
       MyAuthentication m = new MyAuthentication();
       m.protectedSiteAccess();
    */
    
    //Example: Program to demonstrate how to access a password-protected website using JPasswordField.
    void usingJPassField(){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 300);
        f.setLayout(null);
        //Username label
        JLabel ul = new JLabel("Username:");
        ul.setBounds(20,60,100,20);
        //User textfield
        JTextField uf = new JTextField(20);
        uf.setBounds(120, 60, 140, 20);
        //Paswword label
        JLabel pl = new JLabel("Password:");
        pl.setBounds(20,120,100,20);
        //passwordfield
        JPasswordField pf = new JPasswordField(20);
        pf.setBounds(120, 120, 140, 20);
        //Access button
        JButton btn = new JButton("Access");
        btn.setBounds(120, 160, 100, 25);

        f.add(ul);
        f.add(uf);
        f.add(pl);
        f.add(pf);
        f.add(btn);
        
        btn.addActionListener(e->{
            String username = uf.getText();
            char[] password = pf.getPassword();
            
            if(username.isEmpty() || password.length==0){
                JOptionPane.showMessageDialog(f, "Please enter both username and password","Input Error",JOptionPane.ERROR_MESSAGE);
            }
            Authenticator.setDefault(new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication (username,password);
            }
            });
            
            try{
                URL url = new URL("https://httpbin.org/basic-auth/user/passwd");
                URLConnection conn = url.openConnection();
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while((line=reader.readLine())!=null){
                    response.append(line).append("\n");
                }
                reader.close();
                JOptionPane.showMessageDialog(f, "Resource accessed successfully: \n"+response.toString(),"Success",JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(f, "Fialed to access the resource: \n","Error",JOptionPane.ERROR_MESSAGE);
            }
            finally{
                Arrays.fill(password,'0');
            }
        });
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

public class MyNet {
    public static void main(String[] args) throws Exception { 
       MyAuthentication m = new MyAuthentication();
       m.usingJPassField();
    }
}