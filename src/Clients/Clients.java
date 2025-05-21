/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clients;

import java.io.*;
import java.net.*;

/**
 *
 * @author Sohan
 */

public class Clients {
    public static void main(String[] args) {
        //client
        String host = "localhost";
        int port = 80;
        try(Socket socket = new Socket(host, port)){
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Send a message to the server
            out.println("Hello, Server!");
            
            // Read and print the response
            String response = in.readLine();
            System.out.println("Server responded: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}