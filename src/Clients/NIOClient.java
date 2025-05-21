/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clients;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
/**
 *
 * @author Sohan
 */
public class NIOClient {
    public static void main(String[] args) {
        try {
            SocketChannel sc = SocketChannel.open(new InetSocketAddress("localhost", 5000));
            System.out.println("Connected to server.");
            String message = "Hello from client!";
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            sc.write(buffer);
            ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
            int bytesRead = sc.read(responseBuffer);
            if (bytesRead > 0) {
                responseBuffer.flip();
                byte[] data = new byte[responseBuffer.remaining()];
                responseBuffer.get(data);
                String response = new String(data);
                System.out.println("Received from server: " + response);
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
