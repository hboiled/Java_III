/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hboiled.audioplayer;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author 61406
 */
public class AudioPlayer {

    // to store current position 
    private Long currentFrame;
    private Long frameCount;
    private Clip clip;
    private double duration;

    // current status of clip 
    private String status;
    private String nowPlaying;

    private AudioInputStream audioInputStream;
    private static String filePath;

    // constructor to initialize streams and clip 
    public AudioPlayer(String file) throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {

        filePath = file;

        // create AudioInputStream object 
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        frameCount = audioInputStream.getFrameLength();
        duration = ((double) frameCount) / audioInputStream.getFormat().getFrameRate();
        
        // create clip reference 
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip 
        clip.open(audioInputStream);

        // clip.close();
        // clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // Method to play the audio 
    public void play() {
        String songName = FilenameUtils.getBaseName(filePath);

        //start the clip 
        clip.start();

        status = "play";
        nowPlaying = "Now Playing: " + songName;
    }

    // Method to pause the audio 
    public void pause() {
        if (status.equals("paused")) {
            return;
        }

        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
        nowPlaying = "Now Playing: ";
    }

    // Method to resume the audio 
    public void resumeAudio() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        if (status.equals("play")) {
            return;
        }

        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    // Method to restart the audio 
    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException {

        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    // Method to stop the audio 
    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {

        currentFrame = 0L;
        clip.stop();
        clip.close();
        nowPlaying = "Now Playing: ";
    }

    // Method to jump over a specific part 
    // Used in conjunction with slider
    public void jump(long c) throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {

        if (c > 0 && c < clip.getMicrosecondLength()) {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }

    // Method to reset audio stream 
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {

        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    
    public Long getCurrentFrame() {
        return currentFrame;
    }

    public Long getFrameCount() {
        return frameCount;
    }

    public double getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }

    public String getNowPlaying() {
        return nowPlaying;
    }

    public static String getFilePath() {
        return filePath;
    }

}