/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hboiled.audioplayer;

import com.hboiled.audioplayer.Playlist.CellDisplay;
import com.hboiled.audioplayer.Playlist.Playlist;
import com.hboiled.audioplayer.Security.SignInService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author 61406
 */
public class Main extends javax.swing.JFrame {

    private AudioPlayer player;
    private final static String DEFAULTPATH = System.getProperty("user.dir") + "\\sampleSongs";
    // If user is not signed in, disable playlist functionality buttons
    private boolean signedIn;
    private SignInService service;
    private Playlist defaultPlaylist;
    
    private final DefaultListModel<Playlist> playlistModel;
    // songModel is a placeholder for playlists
    private final DefaultListModel<String> songModel;
    
    /**
     * Creates new form Main
     */
    public Main() {
        defaultPlaylist = new Playlist(("default"));
        playlistModel = new DefaultListModel<>();
        songModel = new DefaultListModel<>();
        initComponents();
        service = new SignInService();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadFileChooser = new JFileChooser(DEFAULTPATH);
        FileFilter filter = new FileNameExtensionFilter(".wav file", "wav");
        loadFileChooser.setFileFilter(filter);
        passwordField = new javax.swing.JPasswordField();
        usernameField = new javax.swing.JTextField();
        signUpLbl = new java.awt.Label();
        signInBtn = new javax.swing.JButton();
        regoBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        songsList = new JList(songModel);
        jScrollPane2 = new javax.swing.JScrollPane();
        playlists = new JList(playlistModel);
        title = new java.awt.Label();
        startBtn = new javax.swing.JButton();
        pauseBtn = new javax.swing.JButton();
        stopBtn = new javax.swing.JButton();
        songsLbl = new java.awt.Label();
        playlistLbl = new java.awt.Label();
        playbackSlider = new javax.swing.JSlider();
        resumeBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        previousBtn = new javax.swing.JButton();
        sortPlaylistsBtn = new javax.swing.JButton();
        newPlaylistBtn = new javax.swing.JButton();
        deletePlaylistBtn = new javax.swing.JButton();
        searchPlaylistsBtn = new javax.swing.JButton();
        addSongBtn = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        restartBtn7 = new javax.swing.JButton();
        searchLbl = new java.awt.Label();
        nowPlaying = new java.awt.Label();
        timeStampLbl = new java.awt.Label();
        saveBtn = new javax.swing.JButton();
        createLbl = new java.awt.Label();
        playlistField = new javax.swing.JTextField();
        signedInLbl = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Audio Player");
        setResizable(false);
        setSize(new java.awt.Dimension(460, 320));

        signUpLbl.setText("Sign In");

        signInBtn.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        signInBtn.setText("Sign In");
        signInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInBtnActionPerformed(evt);
            }
        });

        regoBtn.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        regoBtn.setText("Register");
        regoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regoBtnActionPerformed(evt);
            }
        });

        songsList.setCellRenderer(new CellDisplay());
        jScrollPane1.setViewportView(songsList);

        jScrollPane2.setViewportView(playlists);

        title.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        title.setText("JMC Audio Player");

        startBtn.setText("Start");

        pauseBtn.setText("Pause");

        stopBtn.setText("Stop");

        songsLbl.setText("Songs:");

        playlistLbl.setText("Playlists:");

        resumeBtn.setText("Resume");

        nextBtn.setText("Next");

        previousBtn.setText("Previous");

        sortPlaylistsBtn.setText("Sort Playlists");
        sortPlaylistsBtn.setEnabled(false);
        sortPlaylistsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortPlaylistsBtnActionPerformed(evt);
            }
        });

        newPlaylistBtn.setText("New Playlist");
        newPlaylistBtn.setEnabled(false);
        newPlaylistBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPlaylistBtnActionPerformed(evt);
            }
        });

        deletePlaylistBtn.setText("Delete Playlist");
        deletePlaylistBtn.setEnabled(false);
        deletePlaylistBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePlaylistBtnActionPerformed(evt);
            }
        });

        searchPlaylistsBtn.setText("Search Playlists");
        searchPlaylistsBtn.setEnabled(false);
        searchPlaylistsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPlaylistsBtnActionPerformed(evt);
            }
        });

        addSongBtn.setText("Add Song");
        addSongBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSongBtnActionPerformed(evt);
            }
        });

        restartBtn7.setText("Search Songs");

        searchLbl.setText("Search:");

        nowPlaying.setText("Now Playing: ");

        timeStampLbl.setText("00:00 / 00:00");

        saveBtn.setText("Save");

        createLbl.setText("Create Playlist:");

        signedInLbl.setText("Welcome");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(saveBtn)
                            .addComponent(signedInLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nowPlaying, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(signUpLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(signInBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(regoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(playbackSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(timeStampLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(startBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pauseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(resumeBtn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stopBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(playlistField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(sortPlaylistsBtn)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(newPlaylistBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(deletePlaylistBtn))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(nextBtn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(previousBtn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(addSongBtn))
                                    .addComponent(createLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(searchPlaylistsBtn))
                                            .addComponent(searchLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(restartBtn7)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(songsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(playlistLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(playlistLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(signUpLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(signInBtn)
                                .addComponent(regoBtn))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(signedInLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nowPlaying, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playbackSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeStampLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(startBtn)
                                    .addComponent(pauseBtn)
                                    .addComponent(resumeBtn)
                                    .addComponent(stopBtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nextBtn)
                                    .addComponent(previousBtn)
                                    .addComponent(addSongBtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(playlistField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(newPlaylistBtn)
                                    .addComponent(deletePlaylistBtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sortPlaylistsBtn))
                            .addComponent(jScrollPane2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchPlaylistsBtn)
                                        .addComponent(restartBtn7))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(searchLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(22, 22, 22))))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(songsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addSongBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSongBtnActionPerformed
        int result = loadFileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = loadFileChooser.getSelectedFile();
            try {
                //songModel.addElement(selectedFile.getAbsolutePath());
                defaultPlaylist.add(selectedFile.getAbsolutePath());
                insertValuesIntoSongModel();
                player = new AudioPlayer(selectedFile.getAbsolutePath());
                player.play();
                playbackSlider.setValue(0);
                timeStampLbl.setText(String.format("0:00 / 0%.2f", player.getDuration()));
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
            // work with csv files only
            if (FilenameUtils.getExtension(selectedFile.getName()).equals("csv")) {
                //fileModel.addElement(selectedFile);
            } 
        }
    }//GEN-LAST:event_addSongBtnActionPerformed

    private void signInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInBtnActionPerformed
        
        if (!fieldsEmpty()) {
            
            String username = usernameField.getText();
            
            boolean outcome = service.attemptLogin(username, 
                    new String(passwordField.getPassword()));
            
            if (outcome) {
                // extract to enable method
                newPlaylistBtn.setEnabled(true);
                sortPlaylistsBtn.setEnabled(true);
                searchPlaylistsBtn.setEnabled(true);
                deletePlaylistBtn.setEnabled(true);
                signedInLbl.setText(username + "'s");
            }
            
            System.out.println(outcome ? "Signed in" : "Failed");
        }
        
        clearFields();
    }//GEN-LAST:event_signInBtnActionPerformed

    private void regoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regoBtnActionPerformed
        
        if (!fieldsEmpty()) {
            boolean outcome = service.attemptRegister(usernameField.getText(), 
                    new String(passwordField.getPassword()));
            
            System.out.println(outcome ? "Registered" : "Failed");
        }
        
        clearFields();
    }//GEN-LAST:event_regoBtnActionPerformed

    private void newPlaylistBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPlaylistBtnActionPerformed
        if (!playlistField.getText().isBlank()) {
            // check for dupes
            playlistModel.addElement(new Playlist(playlistField.getText()));
        }
        
    }//GEN-LAST:event_newPlaylistBtnActionPerformed

    // Sort functionality, to be refactored
    private void sortPlaylistsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortPlaylistsBtnActionPerformed
        // extract sort method
        List<Playlist> tempList = new ArrayList<>();
        
        for (int i = 0; i < playlistModel.size(); i++) {
            tempList.add(playlistModel.get(i));
        }
        
        Collections.sort(tempList);
        playlistModel.removeAllElements();
        
        playlistModel.addAll(tempList);
        
    }//GEN-LAST:event_sortPlaylistsBtnActionPerformed

    private void searchPlaylistsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPlaylistsBtnActionPerformed

        String subject = searchField.getText();
        int target = -1;
        // set selected playlist entry
        for (int i = 0; i < playlistModel.size(); i++) {
            if (playlistModel.get(i).toString().equals(subject)) {
                target = i;
            }
        }
        if (target >= 0) {
            playlists.setSelectedIndex(target);
        }
        
    }//GEN-LAST:event_searchPlaylistsBtnActionPerformed

    private void deletePlaylistBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePlaylistBtnActionPerformed
        // TODO clear song list 
        int target = playlists.getSelectedIndex();
        
        if (target >= 0) {
            playlistModel.remove(target);
        }
    }//GEN-LAST:event_deletePlaylistBtnActionPerformed

    private void insertValuesIntoSongModel() {
        songModel.removeAllElements();
        
        // replace with current playlist
        List<String> songs = defaultPlaylist.getPlaylist().addToList();
        
        for (String song : songs) {
            songModel.addElement(song);
        }
    }
    
    private boolean fieldsEmpty() {
        return usernameField.getText().isBlank() || passwordField.getPassword().length == 0;
    }
    
    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSongBtn;
    private java.awt.Label createLbl;
    private javax.swing.JButton deletePlaylistBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JFileChooser loadFileChooser;
    private javax.swing.JButton newPlaylistBtn;
    private javax.swing.JButton nextBtn;
    private java.awt.Label nowPlaying;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton pauseBtn;
    private javax.swing.JSlider playbackSlider;
    private javax.swing.JTextField playlistField;
    private java.awt.Label playlistLbl;
    private javax.swing.JList<String> playlists;
    private javax.swing.JButton previousBtn;
    private javax.swing.JButton regoBtn;
    private javax.swing.JButton restartBtn7;
    private javax.swing.JButton resumeBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField searchField;
    private java.awt.Label searchLbl;
    private javax.swing.JButton searchPlaylistsBtn;
    private javax.swing.JButton signInBtn;
    private java.awt.Label signUpLbl;
    private java.awt.Label signedInLbl;
    private java.awt.Label songsLbl;
    private javax.swing.JList<String> songsList;
    private javax.swing.JButton sortPlaylistsBtn;
    private javax.swing.JButton startBtn;
    private javax.swing.JButton stopBtn;
    private java.awt.Label timeStampLbl;
    private java.awt.Label title;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables

    
}
