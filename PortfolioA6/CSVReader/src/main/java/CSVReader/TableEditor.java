/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVReader;

import javax.swing.DefaultCellEditor;
import javax.swing.InputVerifier;
import javax.swing.JTextField;

/**
 *
 * @author 61406
 */
public class TableEditor extends DefaultCellEditor {
    
    private JTextField textField;
    
    public TableEditor(JTextField textField) {
        super(textField);
        this.textField = textField;        
    }
    
    @Override
    public JTextField getComponent() {
        return (JTextField) super.getComponent();
    }
}
