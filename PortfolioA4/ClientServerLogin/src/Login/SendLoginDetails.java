/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author 61406
 */
public class SendLoginDetails {

    private static int portNum = 1234;
    private static String host = "localhost";
    private static String loginServer = "LoginServer";

    public static void SendToServer(String[] details) {

        try (Socket socket = new Socket(host, portNum);
                BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ObjectOutputStream outStream = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));) {

            queryLogin(socket, inStream, outStream, details);

        } catch (UnknownHostException ue) {
            ue.printStackTrace();
            System.out.println(ue.getMessage());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void queryLogin(Socket socket, BufferedReader inStream, ObjectOutputStream outStream, String[] details) {

        String outcome = "";

        try {            
            outStream.writeObject(details);
            outStream.flush();

            String reply = inStream.readLine();
            System.out.println(reply);
//            int inByte;            
//            while ((inByte = inStream.read()) != '\n') {
//                System.out.write(inByte);
//                outcome += inByte;
//            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(outcome);
    }
}
