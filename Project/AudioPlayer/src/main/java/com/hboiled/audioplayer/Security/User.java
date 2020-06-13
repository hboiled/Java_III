/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hboiled.audioplayer.Security;

/**
 *
 * @author 61406
 */
public class User {
    
    private String username;
    private byte[] salt;
    private String securePW;
    
    public User(String name, byte[] salt, String securePW) {
        this.username = name;
        this.salt = salt;
        this.securePW = securePW;
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
    
    @Override
    public String toString() {
        return username;
    }
}
