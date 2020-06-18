/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hboiled.audioplayer.Playlist;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author 61406
 */
public class PlaylistFetcher {

    public static void FindPlaylists(DefaultListModel<Playlist> playlists, String username) {

        String userPath = String.format("data/users/%s", username);
        boolean data = new File(userPath).mkdirs();

        // filter files for csv
        File[] fileList = new File(userPath).listFiles(
                (File dir, String name) -> name.endsWith(".csv"));

        if (fileList.length > 0) {
            for (File f : fileList) {
                playlists.addElement(readFile(f));
            }
        }
    }

    private static Playlist readFile(File file) {
        Playlist newPlaylist = new Playlist(FilenameUtils.
                getBaseName(file.getAbsolutePath()));

        try (CSVReader reader = new CSVReader(new FileReader(file))) {

            // use Reader to read all values into a list
            List<String[]> data = reader.readAll();

            // iterate over all table elements and add the values
            for (String[] row : data) {

                for (String location : row) {
                    newPlaylist.add(location);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return newPlaylist;
    }

    public static void SaveFiles(String username, DefaultListModel<Playlist> playlists) {
        String userPath = String.format("data/users/%s", username);
        boolean data = new File(userPath).mkdirs();

        for (int i = 0; i < playlists.size(); i++) {
            Playlist playlist = playlists.elementAt(i);
            String name = playlist.toString();
            String path = userPath + "/" + name + ".csv";

            try (FileWriter fWriter = new FileWriter(path, false)) {
                for (String location : playlist.getPlaylist().getHelperList()) {
                    fWriter.write(location + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
