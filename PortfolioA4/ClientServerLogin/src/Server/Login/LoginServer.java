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

    private static void insertDummyData() {
        var salt = PWMan.getSalt();
        var securePW = PWMan.SecurePW("admin", salt);
        User u1 = new User("admin", salt, securePW, true);
        users.add(u1);
    }

    private static final PWManager PWMan = new PWManager(new HashGen(), new SaltGen());
    private static UserDatabase users;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // insertDummyData();
        users = new UserDatabase();
        
        // handle one string array sent: username, password
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            //System.out.println("Server listening on port: #" + serverSocket.getLocalPort());
            
            try (Socket clientSocket = serverSocket.accept()) {

                ObjectInputStream inStream;
                inStream = new ObjectInputStream(clientSocket.getInputStream());
                
                DataOutputStream outStream;
                outStream = new DataOutputStream(clientSocket.getOutputStream());
                
                try {
                    String[] details = (String[]) inStream.readObject();
                    System.out.println(details[1]);
                    if (attemptLogin(details)) {
                        outStream.writeBytes("Logged in successfully");
                        outStream.flush();
                    } else {
                        outStream.writeBytes("Login attempt failed.");
                        outStream.flush();
                    }
                } catch (ClassNotFoundException ex) {
                    outStream.writeBytes("ClassNotFound Exception");
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

//    private static boolean processDetails(String[] details) {
//        //System.out.println("I have reached process details");
//        String request = details[0];        
//        
//        if (request.equals("login")) {
//            return attemptLogin(details);
//        }
//        
//        return false;
//    }
    
    private static boolean attemptLogin(String[] details) {
        
        User user = searchUser(details[0]);
        if (user == null) {
            System.out.println("user is null");
            return false;
        }
        
        
        
        String enteredPwSecured = PWMan.SecurePW(details[1], user.getSalt());        
        String securedPw = user.getSecurePW();
        System.out.println(enteredPwSecured + "\n" + securedPw);
        boolean outcome = securedPw.equals(enteredPwSecured);
        
        if (outcome) {
            AdminWindow.main(new String[0]);
        }
        
        return outcome;
    }
    
    // recheck
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
