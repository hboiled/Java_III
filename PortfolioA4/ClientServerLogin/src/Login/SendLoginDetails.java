/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
                DataInputStream inStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());) {

            queryLogin(socket, inStream, outStream, details);

        } catch (UnknownHostException ue) {
            System.out.println(ue.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void queryLogin(Socket socket, DataInputStream inStream, DataOutputStream outStream, String[] details) {

        try {
            
            String query = details[0];
            String username = details[1];
            String password = details[2];

            String lineInput = sc.nextLine();

            if (lineInput.length() > 0) {
                outStream.writeBytes(lineInput);
                outStream.write(13); // carriage return
                outStream.write(10); // line feeding
                outStream.flush();

                if (lineInput.equalsIgnoreCase("quit")) {
                    System.exit(0);
                }

                int inByte;
                System.out.print("Server>>>");
                while ((inByte = inStream.read()) != '\n') {
                    System.out.write(inByte);
                }
                System.out.println("");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
