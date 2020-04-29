/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 61406
 */
public class LoginServer implements Runnable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // handle one string array sent: request type, username, password
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server listening on port: #" + serverSocket.getLocalPort());
            
            try (Socket clientSocket = serverSocket.accept()) {
                
//                String clientHostName = clientSocket.getInetAddress().getHostAddress();
//                int clientPortNum = clientSocket.getLocalPort();
//                System.out.println("Connected from " + clientHostName + " on port #" + clientPortNum);
                
                ObjectInputStream inStream;
                inStream = new ObjectInputStream(clientSocket.getInputStream());
                
                DataOutputStream outStream;
                outStream = new DataOutputStream(clientSocket.getOutputStream());
                
                try {
                    String[] details = (String[]) inStream.readObject();
                    if (processDetails(details)) {
                        outStream.writeBytes("Working OK");
                    }
                } catch (ClassNotFoundException ex) {
                    outStream.writeBytes("ClassNotFound Exception");
                    outStream.writeByte(13);
                    outStream.writeByte(10);
                    outStream.flush();
                }
                
                
                
//                while (true) {
//                    String inLine = inStream.readLine();
//                    System.out.println("Receied from client: " + inLine);
//                    
//                    if (inLine.equalsIgnoreCase("quit")) {
//                        System.out.println("Client disconnected");
//                        break;
//                    }
//                    
//                    String outLine = "You said: " + inLine + ".";
//                    outStream.writeBytes(outLine);
//                    outStream.writeByte(13);
//                    outStream.writeByte(10);
//                    outStream.flush();
//                }
                
                inStream.close();
                outStream.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean processDetails(String[] details) {
        System.out.println("I have reached process details");
        String request = details[0];
        String username = details[1];
        String password = details[2];
        
        return true;
    }

    @Override
    public void run() {
        try {
            main(new String[0]);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
