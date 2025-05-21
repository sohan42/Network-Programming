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
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.channels.FileChannel;
import java.net.InetSocketAddress;

public class NIOClientFile {
    public static void main(String[] args) {
        try {
            // Open client socket and connect to server
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", 5000));
            System.out.println("Connected to server.");

            // File path to read data from
            Path filePath = Paths.get("C:\\Users\\Sohan\\Desktop\\Network Programming\\Files\\input.txt");
            FileChannel fileChannel = FileChannel.open(filePath, StandardOpenOption.READ);

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(buffer) > 0) {
                buffer.flip(); // Prepare buffer for sending
                socketChannel.write(buffer);
                buffer.clear(); // Clear buffer for next read
            }

            System.out.println("File data sent to server.");
            fileChannel.close();
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
