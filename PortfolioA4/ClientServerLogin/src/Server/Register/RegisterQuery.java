/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Register;

import Server.Security.HashGen;
import Server.Security.PWManager;
import Server.Security.SaltGen;
import Server.User;
import Server.UserDatabase;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author 61406
 */
public class RegisterQuery extends UnicastRemoteObject implements RegisterService {

    private UserDatabase userDB;
    private PWManager PWMan;
    
    public RegisterQuery() throws RemoteException {
        super();
        userDB = new UserDatabase();
        PWMan = new PWManager(new HashGen(), new SaltGen());
    }
    
    @Override
    public boolean registerRequest(String[] details) throws RemoteException {
        User newUser = GenUser(details);
        System.out.println(newUser.getUsername());
        return userDB.add(newUser);       
    }
    
    private User GenUser(String[] details) {
        String name = details[0];
        byte[] salt = PWMan.getSalt();
        String SecuredPW = PWMan.SecurePW(details[1], salt);
        
        return new User(name, salt, SecuredPW, false);
    }
    
    
    
}
