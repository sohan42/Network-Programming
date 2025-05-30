/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clients;

/**
 *
 * @author Sohan
 */
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.*;

public class MulticastReceiver {
    public static void main(String[] args) {
        MulticastSocket socket = null;
        try {
            // Bind the MulticastSocket to the same port as the sender
            int port = 5000;
            socket = new MulticastSocket(port);

            // Define the multicast group address
            InetAddress group = InetAddress.getByName("230.0.0.0");
            
            // Join the multicast group so that the socket can receive messages sent to it
            socket.joinGroup(group);
            System.out.println("Joined multicast group: " + group.getHostAddress());

            // Prepare a buffer and a DatagramPacket to receive the data
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Wait for a message from the multicast group (blocking call)
            socket.receive(packet);
            String receivedMessage = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received multicast message: " + receivedMessage);

            // Leave the multicast group when done
            socket.leaveGroup(group);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}

