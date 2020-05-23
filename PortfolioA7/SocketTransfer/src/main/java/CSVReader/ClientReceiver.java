/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVReader;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import static java.lang.System.in;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 61406
 */
public class ClientReceiver implements Runnable {

    private Reader reader;
    private static int portNum = 1235;
    private static String host = "localhost";
    
    public ClientReceiver(Reader reader) {
        this.reader = reader;
    }

    public File receive() {
        File received = null;

        int bytesRead;

        
        try (ServerSocket servSocket = new ServerSocket(portNum);) {
            
            try (Socket clientSocket = servSocket.accept();) {
                DataInputStream inStream = new DataInputStream(clientSocket.getInputStream());
                
                String fileName = inStream.readUTF();
                long fileSize = inStream.readLong();
                byte[] buffer = new byte[1024];
                
                received = new File(fileName);
                
                FileOutputStream outStream = new FileOutputStream(received);

                while (fileSize > 0
                        && (bytesRead = inStream.read(buffer, 0,
                                ((int) Math.min(buffer.length, fileSize)))) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                    fileSize -= bytesRead;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return received;
    }

    @Override
    public void run() {
        System.out.println("test");
        File received = receive();

        if (received != null) {
            System.out.println(received.getName());
            
            reader.start(received);            
            
            
        }
    }

}
