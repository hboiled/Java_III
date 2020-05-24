/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketTransfer;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author 61406
 */
public class FileSender {

    private static int portNum = 1235;
    private static String host = "localhost";

    public static boolean SendToClient(File file) throws IOException {

        byte[] fileBytes = new byte[(int) file.length()];

        try (Socket fileSocket = new Socket(host, portNum);
                DataInputStream inStream = new DataInputStream(
                        new BufferedInputStream(new FileInputStream(file)));
                DataOutputStream outStream = new DataOutputStream(fileSocket.getOutputStream());) {

            // must first write file as bytes into stream so that it can be sent
            inStream.readFully(fileBytes, 0, fileBytes.length);

            // name for identification
            outStream.writeUTF(file.getName());
            // file size so receiver knows how large it is
            outStream.writeLong(fileBytes.length);
            // file as bytes
            outStream.write(fileBytes, 0, fileBytes.length);
            outStream.flush();
            
            // when file is sent, considered success
            // loading the file is responsibility of the Reader
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
