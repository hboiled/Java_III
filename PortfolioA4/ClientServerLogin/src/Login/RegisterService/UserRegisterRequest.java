/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login.RegisterService;

import Server.Register.RegisterService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
/**
 *
 * @author 61406
 */
public class UserRegisterRequest {
    
    public static boolean registerUser(String[] userDetails) throws NotBoundException, MalformedURLException, RemoteException {
        RegisterService service = (RegisterService) Naming.lookup("rmi://localhost:5099/register");
        
        //System.out.println("----" + service.Greet("hello") + service.getClass().getName());
        return service.registerRequest(userDetails);        
    }
    
}
