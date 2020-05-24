/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVReader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author 61406
 */
public class ClientReceiver implements Runnable {

    private static int portNum = 1235;
    private static String host = "localhost";

    public File receive() {
        File received = null;

        int bytesRead;
        
        try (ServerSocket servSocket = new ServerSocket(portNum);) {
            
            try (Socket clientSocket = servSocket.accept();) {
                // input stream using client socket
                DataInputStream inStream = new DataInputStream(clientSocket.getInputStream());
                
                // read file name first for identification
                String fileName = inStream.readUTF();
                // size 
                long fileSize = inStream.readLong();
                // buffer reading of the file
                byte[] buffer = new byte[1024];
                
                // create a File instance using the file name
                received = new File(fileName);
                
                // write bytes to file
                FileOutputStream outStream = new FileOutputStream(received);

                // loop to buffer read the file
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

        // return the file object so that it can be used
        return received;
    }

    // run client on separate thread
    @Override
    public void run() {
        
        File received = receive();

        if (received != null) {
            // start the reader with the received file as an argument
            Reader.start(received);            
            
            
        }
    }

}
