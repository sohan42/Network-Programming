/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clients;

/**
 *
 * @author Sohan
 */
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

public class CompletionExample {
    public static void main(String[] args) {
        try {
            // Open the file for reading asynchronously.
            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(
                Paths.get("C:\\Users\\Sohan\\Desktop\\Network Programming\\Files\\input.txt"), StandardOpenOption.READ
            );
            
            // Allocate a buffer to hold file data.
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            
            // Initiate the asynchronous read with a CompletionHandler.
            fileChannel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer bytesRead, ByteBuffer buf) {
                    buf.flip(); // Prepare buffer for reading.
                    byte[] data = new byte[buf.remaining()];
                    buf.get(data);
                    System.out.println("Read " + bytesRead + " bytes: " + new String(data));
                }

                @Override
                public void failed(Throwable exc, ByteBuffer buf) {
                    System.err.println("File read failed.");
                    exc.printStackTrace();
                }
            });
            
            // The main thread continues its work here without waiting for the read to complete.
            Thread.sleep(2000); // For demonstration: ensure the application doesn't exit immediately.
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

