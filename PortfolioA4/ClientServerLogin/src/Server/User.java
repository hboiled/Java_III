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
    private String salt;
    private String securePW;
    
    public User(String name, String salt, String securePW) {
        this.username = name;
        this.salt = salt;
        this.securePW = securePW;
    }
    
    public String getUsername() {
        return username;
    }

    public String getSalt() {
        return salt;
    }

    public String getSecurePW() {
        return securePW;
    }
}
