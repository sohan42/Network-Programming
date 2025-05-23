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
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.channels.FileChannel;

public class NIOServerFile {
    public static void main(String[] args) {
        try {
            // Open server channel and bind to port 5000
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(5000));
            System.out.println("Server listening on port 5000...");

            while (true) {
                // Accept a client connection
                SocketChannel clientChannel = serverChannel.accept();
                System.out.println("Client connected: " + clientChannel.getRemoteAddress());

                // File path to write received data
                Path filePath = Paths.get("C:\\Users\\Sohan\\Desktop\\Network Programming\\Files\\output.txt");
                Files.createFile(filePath); // Ensure file exists
                FileChannel fileChannel = FileChannel.open(filePath, StandardOpenOption.WRITE);

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (clientChannel.read(buffer) > 0) {
                    buffer.flip(); // Prepare buffer for writing
                    fileChannel.write(buffer);
                    buffer.clear(); // Clear buffer for next read
                }

                System.out.println("Data received and written to file.");
                fileChannel.close();
                clientChannel.close(); // Close connection
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}