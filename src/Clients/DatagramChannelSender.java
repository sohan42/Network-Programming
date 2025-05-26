/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clients;

/**
 *
 * @author Sohan
 */

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelSender {
    public static void main(String[] args) {
        try {
            // Open a DatagramChannel
            DatagramChannel channel = DatagramChannel.open();
            
            // (Optional) Configure non-blocking mode
            channel.configureBlocking(true); // or false for non-blocking
            
            // Prepare data to send
            String msg = "Hello via DatagramChannel!";
            ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
            
            // Define the target address and port
            InetSocketAddress targetAddress = new InetSocketAddress("localhost", 9876);
            
            // Send the data
            channel.send(buffer, targetAddress);
            System.out.println("Message sent: " + msg);
            
            // Close the channel
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

