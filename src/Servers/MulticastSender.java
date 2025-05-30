/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servers;

/**
 *
 * @author Sohan
 */

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSender {
    public static void main(String[] args) {
        MulticastSocket socket = null;
        try {
            // Create a MulticastSocket (unbound; no need to join a group for sending)
            socket = new MulticastSocket();

            // Define the multicast group and port
            InetAddress group = InetAddress.getByName("230.0.0.0");
            int port = 5000;

            // Prepare the message to be sent
            String message = "Alert message!";
            byte[] buffer = message.getBytes();
            
            // Create a DatagramPacket with the message, group address, and specified port
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
            
            // Optionally, set the Time-to-Live (TTL) for the packet (4 hops in this case)
            socket.setTimeToLive(4);
            
            // Send the message to the multicast group
            socket.send(packet);
            System.out.println("Multicast message sent: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {	
                socket.close();
            }
        }
    }
}

