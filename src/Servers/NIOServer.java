/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sohan
 */

package Servers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOServer {
    public static void main(String[] args) {
          try {
            ServerSocketChannel sc = ServerSocketChannel.open();
            sc.bind(new InetSocketAddress(5000));
            System.out.println("Server started on port 5000...");
            while (true) {
                SocketChannel cc = sc.accept();
                System.out.println("Accepted connection from: " + cc.getRemoteAddress());
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int bytesRead = cc.read(buffer);
                if (bytesRead > 0) {
                    buffer.flip();
                    byte[] data = new byte[buffer.remaining()];
                    buffer.get(data);
                    String message = new String(data);
                    System.out.println("Received: " + message);
                    String response = "Stay alrt!";
                    ByteBuffer responseBuffer = ByteBuffer.wrap(response.getBytes());
                    cc.write(responseBuffer);
                }
                cc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}