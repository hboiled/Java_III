/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hboiled.audioplayer.Security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 61406
 */
public class SaltGen {
    private SecureRandom secureRand;
    private int saltSize;
    
    public SaltGen() {
        saltSize = 16;
    }
    
    public byte[] getSalt() {
        try {
            secureRand = SecureRandom.getInstance("SHA1PRNG");
            byte[] saltArr = new byte[saltSize];
            secureRand.nextBytes(saltArr);
            
            return saltArr;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SaltGen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

