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
    
    public RegisterQuery() throws RemoteException {
        super();
    }
    
    @Override
    public boolean registerRequest(String[] details) throws RemoteException {
        UserDatabase userDB = new UserDatabase();
        System.out.println(details[0]);
                
        User newUser = GenUser(details);
        System.out.println(newUser.getUsername());
        return userDB.add(newUser);       
    }
    
    private User GenUser(String[] details) {
        PWManager PWMan = new PWManager(new HashGen(), new SaltGen());
        
        String name = details[0];
        byte[] salt = PWMan.getSalt();
        String SecuredPW = PWMan.SecurePW(details[1], salt);
        
        return new User(name, salt, SecuredPW, false);
    }
    
    
    
}
