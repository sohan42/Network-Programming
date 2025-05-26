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
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPEchoServer {
    private static final int PORT = 9876;  // Choose a port to listen on
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // 1. Create and bind the socket to a port.
            socket = new DatagramSocket(PORT);
            System.out.println("UDP Echo Server is listening on port " + PORT);

            // Infinite loop to listen for incoming packets.
            while (true) {
                // 2. Prepare a buffer and a DatagramPacket for receiving the data.
                byte[] rcvBuf = new byte[BUFFER_SIZE];
                DatagramPacket rcvPckt = new DatagramPacket(rcvBuf, rcvBuf.length);
                
                // 3. Block until a packet is received.
                socket.receive(rcvPckt);
                
                //Sender's details
                InetAddress cAddress = rcvPckt.getAddress();
                int cPort = rcvPckt.getPort();
                
                System.out.println("Received from "+ cAddress+ ":" + cPort);
                String rcvTxt = new String(rcvPckt.getData(), 0, rcvPckt.getLength());
                System.out.println("Message: "+ rcvTxt);

                // 4. Echo the same message back to the sender.
                DatagramPacket echoPacket = new DatagramPacket(rcvPckt.getData(), rcvPckt.getLength(), cAddress, 	   cPort);
                socket.send(echoPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. Close the socket when finished.
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

