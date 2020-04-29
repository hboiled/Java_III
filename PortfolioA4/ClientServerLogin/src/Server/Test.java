/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Server.Security.PWManager;
import Server.Security.SaltGen;
import Server.Security.HashGen;

/**
 *
 * @author 61406
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SaltGen sg = new SaltGen();
        HashGen hg = new HashGen();
        PWManager pwm = new PWManager(hg, sg);
        
        String pw1 = "password1";
        String pw2 = "password1";
        String pw3 = "hunter2";
        
        byte[] saltp1 = pwm.getSalt();
        byte[] saltp2 = pwm.getSalt();
        byte[] saltp3 = pwm.getSalt();
        
        String hgp1 = pwm.SecurePW(pw1, saltp1);
        String hgp2 = pwm.SecurePW(pw2, saltp1);
        String hgp3 = pwm.SecurePW(pw3, saltp3);
        
        System.out.println(hgp1);
        System.out.println(hgp2);
        System.out.println(hgp3);
        
    }
    
}
