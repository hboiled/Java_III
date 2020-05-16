/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVReader;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author 61406
 */
public class Verifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        JTextField field = (JTextField) input;
        
        boolean fiveCharLimit = field.getText().length() <= 5;
        return false;
    }
    
}
