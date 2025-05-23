/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servers;

/**
 *
 * @author Sohan
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) {
        final int PORT = 4445;
        DatagramSocket socket = null;
        byte[] buffer = new byte[256];
        boolean running = true;
        try {
            // Bind the socket to the specified port.
            socket = new DatagramSocket(PORT);
            System.out.println("UDP Server started on port " + PORT);

            // Loop to continuously receive and send message.
            while (running) {
                // Prepare a packet to receive incoming data.
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // Retrieve sender information
                InetAddress CAddress = packet.getAddress();
                System.out.println("Client address: "+CAddress);
                int CPort = packet.getPort();
                String rMessage = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + rMessage);

                // Check for termination command
                if (rMessage.trim().equalsIgnoreCase("end")) {
                    running = false;
                    System.out.println("Shutting down the server...");
                    continue;
                }
                
                // clear buffer for sending the message.
                byte[] sBuffer = new byte[256];
                String msg = "Server is listenning...";
                sBuffer = msg.getBytes();
                
                // send the message to the client.
                DatagramPacket response = new DatagramPacket(sBuffer,sBuffer.length,CAddress,CPort);
 
                // Send the packet through the UDP socket.
                socket.send(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Ensure the socket is closed to release system resources.
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

