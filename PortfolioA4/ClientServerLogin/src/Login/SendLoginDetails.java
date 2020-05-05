/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.BufferedReader;
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

    public static String SendToServer(String[] details) {

        String outcomeMsg = "";
        
        try (Socket socket = new Socket(host, portNum);
                BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ObjectOutputStream outStream = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));) {

            outcomeMsg = queryLogin(socket, inStream, outStream, details);

        } catch (UnknownHostException ue) {
            ue.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        return outcomeMsg;
    }

    private static String queryLogin(Socket socket, BufferedReader inStream, ObjectOutputStream outStream, String[] details) {

        // send request then return reply msg
        String outcome = "";

        try {            
            outStream.writeObject(details);
            outStream.flush();

            String reply = inStream.readLine();
            outcome = reply;
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return outcome;
    }
}
