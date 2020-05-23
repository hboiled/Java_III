/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketTransfer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 61406
 */
public class FileSender {

    private static int portNum = 1235;
    private static String host = "localhost";

    public static void SendToClient(File file) throws IOException {

        byte[] fileBytes = new byte[(int) file.length()];

        try (Socket fileSocket = new Socket(host, portNum);
                DataInputStream inStream = new DataInputStream(
                        new BufferedInputStream(new FileInputStream(file)));
                DataOutputStream outStream = new DataOutputStream(fileSocket.getOutputStream());) {

            inStream.readFully(fileBytes, 0, fileBytes.length);

            // name
            outStream.writeUTF(file.getName());
            // file size
            outStream.writeLong(fileBytes.length);
            // file
            outStream.write(fileBytes, 0, fileBytes.length);
            outStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public static String SendToClient(File file) {
//
//        String outcomeMsg = "";
//        
//        try (Socket socket = new Socket(host, portNum);
//                BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
//                OutputStream outStream = socket.getOutputStream();) {
//
//            outcomeMsg = queryTransfer(socket, inStream, outStream, file);
//
//        } catch (UnknownHostException ue) {
//            ue.printStackTrace();
//            System.exit(1);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//        
//        return outcomeMsg;
//    }
//
//    private static String queryTransfer(Socket socket, BufferedInputStream inStream, OutputStream outStream, File file) {
//
//        // send request then return reply msg
//        String outcome = "";
//
//        try {      
//            byte[] fileSize = new byte[(int) file.length()];
//            inStream.read(fileSize, 0, fileSize.length);
//            outStream.write(fileSize, 0, fileSize.length);
//            outStream.flush();
//            
//            // 
////            String reply = inStream.readLine();
////            outcome = reply;
//            
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//
//        return outcome;
//    }
}
