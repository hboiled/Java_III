/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Login;

import Server.AdminWindow;
import Server.Security.HashGen;
import Server.Security.SaltGen;
import Server.Security.PWManager;
import Server.User;
import Server.UserDatabase;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 61406
 */
public class LoginServer implements Runnable {

    private static final PWManager PWMan = new PWManager(new HashGen(), new SaltGen());
    private static UserDatabase users;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        users = new UserDatabase();

        // handle one string array sent: username, password
        try (ServerSocket serverSocket = new ServerSocket(1234)) {

            try (Socket clientSocket = serverSocket.accept()) {

                ObjectInputStream inStream;
                inStream = new ObjectInputStream(clientSocket.getInputStream());

                DataOutputStream outStream;
                outStream = new DataOutputStream(clientSocket.getOutputStream());

                try {
                    String[] details = (String[]) inStream.readObject();
                    System.out.println(details[1]);
                    switch (attemptLogin(details)) {
                        case "success":
                            sendMsg("Logged in successfully", outStream);
                            break;
                        case "admin":
                            sendMsg("Admin logged in", outStream);
                            break;
                        case "failed":
                            sendMsg("Login failed", outStream);
                            break;
                        case "User does not exist":
                            sendMsg("User does not exist", outStream);
                            break;
                        default:
                            sendMsg("Error! No message found", outStream);
                            break;
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    sendMsg("Classcast Exception encountered", outStream);
                }

                inStream.close();
                outStream.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // use dataoutputstream to send reply back to client
    private static void sendMsg(String msg, DataOutputStream outStream) throws IOException {
        outStream.writeBytes(msg);
        outStream.flush();
    }

    private static String attemptLogin(String[] details) {

        User user = searchUser(details[0]);
        if (user == null) {
            return "User does not exist";
        }

        String enteredPwSecured = PWMan.SecurePW(details[1], user.getSalt());
        String securedPw = user.getSecurePW();
        
        System.out.println(enteredPwSecured + "\n" + securedPw);
        
        boolean PWCheckOutcome = securedPw.equals(enteredPwSecured);

        if (PWCheckOutcome && user.isAdmin()) {
            AdminWindow.main(new String[0]);
            return "admin";
        }

        return PWCheckOutcome ? "success" : "failed";
    }

    private static User searchUser(String name) {
        for (User u : users.getUsers()) {
            if (u.getUsername().equals(name)) {
                return u;
            }
        }

        return null;
    }

    // enable run on a separate thread
    @Override
    public void run() {
        try {
            main(new String[0]);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
