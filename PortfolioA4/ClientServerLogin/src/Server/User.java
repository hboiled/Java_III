/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.Serializable;

/**
 *
 * @author 61406
 */

public class User implements Serializable {
    
    private String username;
    private byte[] salt;
    private String securePW;
    private boolean isAdmin;
    
    public User(String name, byte[] salt, String securePW, boolean isAdmin) {
        this.username = name;
        this.salt = salt;
        this.securePW = securePW;
        this.isAdmin = isAdmin;
    }
    
    public String getUsername() {
        return username;
    }

    public byte[] getSalt() {
        return salt;
    }

    public String getSecurePW() {
        return securePW;
    }
    
    public boolean isAdmin() {
        return isAdmin;
    }
    
    @Override
    public String toString() {
        return username;
    }
}
