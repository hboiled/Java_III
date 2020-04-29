/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author 61406
 */
public class LoginServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // handle one string array sent: request type, username, password
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server listening on port: #" + serverSocket.getLocalPort());
            
            try (Socket clientSocket = serverSocket.accept()) {
                
                String clientHostName = clientSocket.getInetAddress().getHostAddress();
                int clientPortNum = clientSocket.getLocalPort();
                System.out.println("Connected from " + clientHostName + " on port #" + clientPortNum);
                
                BufferedReader inStream;
                inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                
                DataOutputStream outStream;
                outStream = new DataOutputStream(clientSocket.getOutputStream());
                
                while (true) {
                    String inLine = inStream.readLine();
                    System.out.println("Receied from client: " + inLine);
                    
                    if (inLine.equalsIgnoreCase("quit")) {
                        System.out.println("Client disconnected");
                        break;
                    }
                    
                    String outLine = "You said: " + inLine + ".";
                    outStream.writeBytes(outLine);
                    outStream.writeByte(13);
                    outStream.writeByte(10);
                    outStream.flush();
                }
                
                inStream.close();
                outStream.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
