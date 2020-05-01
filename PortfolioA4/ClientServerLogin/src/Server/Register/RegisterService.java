/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Register;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author 61406
 */
public interface RegisterService extends Remote {
    boolean registerRequest(String[] details) throws RemoteException;
}
