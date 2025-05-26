/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clients;

/**
 *
 * @author Sohan
 */

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class UDPSender {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            String message = "Hello, UDP!";
            byte[] data = message.getBytes();
            InetAddress receiverAddress = InetAddress.getByName("localhost");
            int port = 9876;
            DatagramPacket packet = new DatagramPacket(data, data.length, receiverAddress, port);
            socket.send(packet);
            System.out.println("Datagram sent successfully!");
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

