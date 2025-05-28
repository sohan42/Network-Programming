/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servers;

/**
 *
 * @author Sohan
 */

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.net.SocketAddress;

public class DatagramChannelReceiver{
    public static void main(String[] args) {
        try {
            // Open a DatagramChannel and bind it to a port
            DatagramChannel channel = DatagramChannel.open();
            channel.socket().bind(new InetSocketAddress(9876));
            System.out.println("Receiver is waiting on port 9876...");
            
            // Buffer to hold the incoming data
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            
            // Receive data (blocking call; use non-blocking with a Selector in advanced use cases)
            SocketAddress senderAddress = channel.receive(buffer);
            if (senderAddress != null) {
                buffer.flip(); // Prepare to read the data from the buffer
                byte[] rBytes = new byte[buffer.remaining()];
                buffer.get(rBytes);
                String rMsg = new String(rBytes);
                System.out.println("Received message from: " + senderAddress);
                System.out.println("Message: " + rMsg);
            }
            // Close the channel
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 

