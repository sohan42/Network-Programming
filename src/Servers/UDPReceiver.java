/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servers;

/**
 *
 * @author Sohan
 */

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class UDPReceiver {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9876);
            System.out.println("Receiver is waiting for a datagram on port 9876...");
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            System.out.println("Datagram received!");
            String receivedMessage = new String(packet.getData(), 0, packet.getLength());
            InetAddress senderAddress = packet.getAddress();
            int senderPort = packet.getPort();
            System.out.println("Message: " + receivedMessage);
            System.out.println("Received from: " + senderAddress + ":" + senderPort);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

