/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clients;

import java.util.Date;
import javax.net.ssl.*;
/**
 *
 * @author Sohan
 */
public class Client2 {
    //SSL Client
    public static void main(String[] args){
       String host = "localhost";
    	try {
            // Obtain the default SSLSocketFactory and create an SSLSocket
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(host, 443);

            // Start the handshake explicitly
            socket.startHandshake();

            // Retrieve the established SSLSession
            SSLSession session = socket.getSession();

            // Get session ID
            byte[] sessionId = session.getId();
            System.out.println("Session ID: " + bytesToHex(sessionId));

            // Get cipher suite
            System.out.println("Cipher Suite: " + session.getCipherSuite());

            // Get creation and last accessed time
            long creation_date = session.getCreationTime();
            System.out.println("Creation date: " + new Date(creation_date));
            
            long accessed_date = session.getLastAccessedTime();
            System.out.println("Accessed date: " + new Date(accessed_date));
           
            // Get buffer sizes
            System.out.println("Packet Buffer Size: " + session.getPacketBufferSize()/1024 +" KB");
            System.out.println("Application Buffer Size: " + session.getApplicationBufferSize()/1024 +" KB");

            // Get peer information
            System.out.println("Peer Host: " + session.getPeerHost());
            System.out.println("Peer Port: " + session.getPeerPort());

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Utility method for converting a byte array to a hexadecimal string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b)); // %02x: an integer to its hexadecimal representation
        }
        return hexString.toString();
    }   
}
