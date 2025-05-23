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
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class SelectorExample {
    public static void main(String[] args) {
        try {
            // Create a selector using the system's default provider.
            Selector selector = Selector.open();

            // Open a server socket channel.
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(8080));
            
            // Configure the server channel to be non-blocking.
            serverChannel.configureBlocking(false);
            
            // Register the server channel with the selector for accept events.
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server is listening on port 8080...");

            // Continuously listen for I/O events.
            while (true) {
                // The select() method blocks until at least one channel is ready.
                int readyChannels = selector.select();
                if (readyChannels == 0) continue;

                // Obtain the selected keys (channels ready for I/O).
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    
                    // Check if the key is valid and if the channel is ready to accept a new connection.
                    if (key.isAcceptable()) {
                        ServerSocketChannel srvChannel = (ServerSocketChannel) key.channel();
                        SocketChannel clientChannel = srvChannel.accept();
                        clientChannel.configureBlocking(false);
                        // Register the new connection for read events.
                        clientChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("Accepted a new connection.");
                    }
                    
                    // Check if the channel is ready to read data.
                    if (key.isReadable()) {
                        // Read data from the channel as needed.
                        System.out.println("Channel ready for reading.");
                        // You might include your read operations here.
                    }
                    
                    // Remove the key from the selected set; it is important
                    // so that it isn’t processed again in the next iteration.
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
