/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hboiled.audioplayer.Playlist;

import java.awt.Component;
import java.io.File;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author 61406
 */
public class CellDisplay extends DefaultListCellRenderer {

    // render the Jlist display so that it shows the file name only
    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        String file = (String) value;
        setText(FilenameUtils.getBaseName(file));
        return this;
    }

}
