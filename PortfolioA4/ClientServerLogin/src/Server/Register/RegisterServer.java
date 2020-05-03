/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Register;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 61406
 */
public class RegisterServer implements Runnable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {
        Registry registry;
        try {
            registry = LocateRegistry.createRegistry(5099);            
        } catch (RemoteException e) {
            registry = LocateRegistry.getRegistry(5099);            
        }
        
        registry.rebind("register", new RegisterQuery());
        
    }

    
    @Override
    public void run() {
        try {
            main(new String[0]);
        } catch (RemoteException ex) {
            Logger.getLogger(RegisterServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
