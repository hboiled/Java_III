
import com.hboiled.audioplayer.Security.HashGen;
import com.hboiled.audioplayer.Security.PWManager;
import com.hboiled.audioplayer.Security.SaltGen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 61406
 */
public class PWManagerTest {
    private PWManager PWM;
    
    
    @BeforeEach
    public void init() {
        PWM = new PWManager(new HashGen(), new SaltGen());
    }
    
    @Test
    public void securePW_SameSaltAndPWShouldReturnSameValue() {
        
        String password = "test";
        byte[] salt = PWM.getSalt();
        
        String SPW = PWM.SecurePW(password, salt);
        
        Assertions.assertEquals(1,1);
        
    }
    
}
