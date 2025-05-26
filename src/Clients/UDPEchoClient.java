/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clients;

/**
 *
 * @author Sohan
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPEchoClient {
    private static final int SERVER_PORT = 9876;  // Port where the server is listening
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        DatagramSocket socket = null;
        try (Scanner scanner = new Scanner(System.in)) {
            // 1. Create a DatagramSocket (binds automatically to an ephemeral port).
            socket = new DatagramSocket();

            // 2. Specify the server address.
            InetAddress sAddress = InetAddress.getByName("localhost");  // Use the server IP or hostname

            // 3. Enter message to send
            System.out.print("Enter a message to send to the echo server: ");
            String msg = scanner.nextLine();
            byte[] sndBuf = msg.getBytes();

            // 4. Create a DatagramPacket with the message.
            DatagramPacket sendPacket = new DatagramPacket(
                    sndBuf, sndBuf.length, sAddress, SERVER_PORT);

            // 5. Send the datagram to the server.
            socket.send(sendPacket);
            System.out.println("Message sent to server: " + msg);

            // 6. Prepare a buffer and DatagramPacket for the echoed response.
            byte[] rBuffer = new byte[BUFFER_SIZE];
            DatagramPacket rPacket = new DatagramPacket(rBuffer, rBuffer.length);

            // 7. Receive the echoed response (blocking call).
            socket.receive(rPacket);
            String echoMsg = new String(rPacket.getData(), 0, rPacket.getLength());
            System.out.println("Received echo from server: " + echoMsg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 8. Close the socket.
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
