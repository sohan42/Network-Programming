/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clients;

/**
 *
 * @author Sohan
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        
        try {
            // Create a DatagramSocket. The OS assigns an ephemeral port if none specified.
            socket = new DatagramSocket();

            // Define server address and server port (example: localhost and port 4445)
            InetAddress SAddress = InetAddress.getByName("localhost");
            int SPort = 4445;

            // Prepare message data to be sent
            String msg = "Hello UDP Server";
            byte[] buffer = msg.getBytes();

            // Create a DatagramPacket to encapsulate the data for transmission.
            DatagramPacket sPacket = new DatagramPacket(buffer, buffer.length, SAddress, SPort);

            // Send the packet through the UDP socket.
            socket.send(sPacket);
            System.out.println("Message sent to server: " + msg);

            // Prepare buffer for receiving the message.
            byte[] rBuffer = new byte[256];
            DatagramPacket rPacket = new DatagramPacket(rBuffer, rBuffer.length);

            // Receive the response (this is a blocking call).
            socket.receive(rPacket);

            // Convert the receiveBuffer content to a string and print.
            String rMessage = new String(rPacket.getData(), 0, rPacket.getLength());
            System.out.println("Message from server: " + rMessage);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Always close the socket to free up resources.
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

