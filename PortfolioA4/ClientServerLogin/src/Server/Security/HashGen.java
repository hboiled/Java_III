/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 61406
 */
public class HashGen {
    private MessageDigest msgDigest;
    
    public String HashSaltPW(byte[] salt, String pw) {
        String genPW = null;
        
        try {
            msgDigest = MessageDigest.getInstance("SHA-512");
            msgDigest.update(salt);
            
            byte[] pwHash = msgDigest.digest(pw.getBytes(StandardCharsets.UTF_8));
                        
            genPW = Base64.getEncoder().encodeToString(pwHash);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashGen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return genPW;
    }
}
