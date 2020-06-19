/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecurityTests;

import com.hboiled.audioplayer.Security.HashGen;
import com.hboiled.audioplayer.Security.PWManager;
import com.hboiled.audioplayer.Security.SaltGen;
import com.hboiled.audioplayer.Security.SignInService;
import com.hboiled.audioplayer.Security.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author 61406
 */
public class SignInServiceTests {
    SignInService service;
    PWManager pwm;
    
    @BeforeEach
    public void init() {
        pwm = new PWManager(new HashGen(), new SaltGen());
        service = new SignInService();        
    }
    
    @Test
    public void attemptLogin_WorksWithValidUser() {
        // create valid users
        genUsers();
        
        String u1 = "a";
        String pw1 = "aaa";
        
        String u2 = "b";
        String pw2 = "bbb";
        
        boolean outcome1 = service.attemptLogin(u1, pw1);
        boolean outcome2 = service.attemptLogin(u2, pw2);
        
        Assertions.assertTrue(outcome1);
        Assertions.assertTrue(outcome2);
    }
    
    @Test
    public void attemptRegister_FailsWIthDuplicateUser() {
        genUsers();
        
        boolean outcome = service.attemptRegister("a", "aaa");
        
        Assertions.assertFalse(outcome);
    }
    
    private void genUsers() {
        byte[] salt1 = pwm.getSalt();
        byte[] salt2 = pwm.getSalt();
        byte[] salt3 = pwm.getSalt();
        
        service.getUsers().add(new User("a", salt1, pwm.SecurePW("aaa", salt1)));
        service.getUsers().add(new User("b", salt2, pwm.SecurePW("bbb", salt2)));
        service.getUsers().add(new User("c", salt3, pwm.SecurePW("ccc", salt3)));
    }
}
