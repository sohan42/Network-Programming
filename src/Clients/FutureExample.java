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
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class FutureExample {
     public static void main(String[] args) {
        try {
            // Open the file channel for asynchronous reading.
            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(
                    Paths.get("C:\\Users\\Sohan\\Desktop\\Network Programming\\Files\\input.txt"), StandardOpenOption.READ);
            
            // Allocate a buffer to hold file data.
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            
            // Initiate the asynchronous read operation from position 0.
            Future<Integer> futureResult = fileChannel.read(buffer, 0);
            
            // Meanwhile, the main thread is free to perform other tasks or periodically check status.
            while (!futureResult.isDone()) {
                System.out.println("Reading file asynchronously...");
                Thread.sleep(100); // Sleep briefly to simulate work.
            }
            
            // Once the future indicates completion, retrieve the number of bytes read.
            int bytesRead = futureResult.get();
            System.out.println("Bytes read: " + bytesRead);
            
            // Prepare the buffer for reading and print out the file content.
            buffer.flip();
            byte[] data = new byte[buffer.limit()];
            buffer.get(data);
            System.out.println("File content: " + new String(data));
            
            // Close the file channel.
            fileChannel.close();
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
     }
}
