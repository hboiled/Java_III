/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Security;

/**
 *
 * @author 61406
 */
public class PWManager {
    private HashGen hashGen;
    private SaltGen saltGen;
    
    public PWManager(HashGen hash, SaltGen salt) {
        hashGen = hash;
        saltGen = salt;
    }
    
    public String SecurePW(String password, byte[] salt) {
        return hashGen.HashSaltPW(salt, password);
    }
    
    public byte[] getSalt() {
        return saltGen.getSalt();
    }
}
